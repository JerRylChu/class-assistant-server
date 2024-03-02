package com.zjr.assistant.service.spark

import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util
import java.util.Objects

import breeze.numerics.sqrt
import com.zjr.assistant.controller.FileController
import com.zjr.assistant.utils.DBConnectionPoolExecutor
import org.apache.commons.io.FileUtils
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.system.ApplicationHome
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Scheduled

@Service
class ResService @Autowired()(redisTemplate: RedisTemplate[String, Object], dBConnectionPoolExecutor: DBConnectionPoolExecutor) {
  val MIN_RATES=2
  val PATH=System.getProperty("user.dir")

  @Scheduled(fixedDelay = 24*60*60*1000)
  def recommendModel(): Unit = {
    println("开始训练模型--"+LocalDateTime.now())
    val sparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("Recommend")
      .set("spark.testing.memory", "524288000")
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    spark.sparkContext.setCheckpointDir("checkpoint")

    //加载数据
    val url = "jdbc:mysql://42.192.230.241:3306/assistant?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false" +
      "&user=root&password=ZJR199925"

    val rateDF = spark.read.format("jdbc")
      .option("url", url).option("dbtable", "Rate")
      .option("numPartitions", 20).option("lowerBound", "0").option("upperBound", "200").option("partitionColumn", "uid").load()

    val rateRDD = rateDF.rdd
      .map(rating => {
        val scores = rating.get(2).asInstanceOf[Number].doubleValue
        Rating(rating.getInt(0), rating.getInt(1), scores-1)
      }).cache()
    bestmodelsave(spark,rateRDD)
  }

  def bestmodelsave(spark:SparkSession,rateRDD:RDD[Rating]): Unit ={
    val splits = rateRDD.randomSplit(Array(0.8, 0.2))
    val traingRDD = splits(0).cache()
    val testRDD = splits(1).cache()
    val lambdas = Array(0.1,1)
    val rank = 20
    val numIterations = Array(10, 20)
    var numIter = 0;
    var lamb: Double = 0;
    var bestmodel: MatrixFactorizationModel = null
    var MSE: Double = 2019.1211;
    for (lam <- lambdas) {
      for (iter <- numIterations) {
        val model = ALS.train(traingRDD, rank, iter, lam)
        //测试集rating
        val theRMSE = computeRmse(model, testRDD)
        if (MSE == 2019.1211 || theRMSE <= MSE) {
          MSE = theRMSE
          numIter = iter
          lamb = lam
          bestmodel = model
        }
        println("rank=" + rank, "numIterations" + iter, "lamb" + lam, "RMSE" + theRMSE)
      }
    }
    //保存模型
    val file : File= new File(PATH+"/bestmodel")
    if(file.exists() && file.list().length > 0){
      FileUtils.deleteDirectory(file);
    }
    bestmodel.save(spark.sparkContext,PATH+"/bestmodel")
    println("模型保存成功--"+LocalDateTime.now())

  }

  @Scheduled(fixedDelay = 10*60*1000)
  def recommend(): Unit={
    println("调用模型开始计算--"+LocalDateTime.now())
    val sparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("Recommend")
      .set("spark.testing.memory", "524288000")
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    //加载数据
    val url = "jdbc:mysql://42.192.230.241:3306/assistant?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false" +
      "&user=root&password=ZJR199925"
    val ArticleRatingsDF = spark.read.format("jdbc")
      .option("url", url).option("dbtable", "ArticleRates")
      .option("numPartitions", 20).option("lowerBound", "0").option("upperBound", "200").option("partitionColumn", "aid").load()

    val rateDF = spark.read.format("jdbc")
      .option("url", url).option("dbtable", "Rate")
      .option("numPartitions", 20).option("lowerBound", "0").option("upperBound", "200").option("partitionColumn", "uid").load()

    val usersDF = spark.read.format("jdbc")
      .option("url", url).option("dbtable", "assistant_user")
      .option("numPartitions", 20).option("lowerBound", "0").option("upperBound", "200").option("partitionColumn", "id").load()

    val ArticlesDF = spark.read.format("jdbc")
      .option("url", url).option("dbtable", "article")
      .option("numPartitions", 20).option("lowerBound", "0").option("upperBound", "200").option("partitionColumn", "id").load()

    //创建临时表
    ArticleRatingsDF.createOrReplaceTempView("SumRatings")
    usersDF.createOrReplaceTempView("users")
    ArticlesDF.createOrReplaceTempView("articles")
    val ArticleDF = spark.sql("select id,major,create_time from articles")

    val rateRDD = rateDF.rdd
      .map(rating => {
        val scores = rating.get(2).asInstanceOf[Number].doubleValue
        Rating(rating.getInt(0), rating.getInt(1), scores-1)
      })
      .cache()

    val bestmodel=MatrixFactorizationModel.load(spark.sparkContext,PATH+"/bestmodel")

    val colduserslist: util.ArrayList[Integer] = new util.ArrayList[Integer]()
    val newusersDF = usersDF.select("id").except(rateDF.select("uid").distinct()).toDF()
    newusersDF.collect().foreach(x => {
      colduserslist.add(x.getInt(0))
    })
    usersDF.select("id").except(newusersDF).collect.foreach(user => {
      val mylist: util.ArrayList[Integer] = filterArticle(rateRDD,ArticleDF,user.getInt(0))
      val predictAritclelist: util.ArrayList[Integer] = new util.ArrayList[Integer]()
      val predictProducts = bestmodel.recommendProducts(user.getInt(0), 60).filter(x => x.rating >= 0).foreach(x => {
        predictAritclelist.add(x.product)
      })

      mylist.forEach(x => {
        if (predictAritclelist.contains(x))
          predictAritclelist.remove(x)
      })
      redisTemplate.opsForValue().set("recommendlist"+user.getInt(0),predictAritclelist)
      //冷启动用户判断
      if (predictAritclelist.size() <= 20) {
        colduserslist.add(user.getInt(0))
      }
    })
    println("ALS推荐完成")
    println("colduserlist:"+colduserslist.size())
    println(colduserslist)
    val rowlist: util.ArrayList[Row] = new util.ArrayList[Row]
    colduserslist.forEach(user=>{
      usersDF.select("id","major").where(s"id==${user}").collect().foreach(x=>{
        val row=Row(user,x.getInt(1))
        rowlist.add(row)
      })
    })
    rowlist.forEach(x=>{
      val recAritclelist: util.ArrayList[Integer] = new util.ArrayList[Integer]()
      ArticleDF.sort(ArticleDF("id").desc).where(s"major==${x.getInt(1)}").head(20).foreach(x=>{
        recAritclelist.add(x.getInt(0))
      })
      ArticleRatingsDF.where(s"major=${x.getInt(1)}").select("aid").head(30).foreach(x=>{
        recAritclelist.add(x.getInt(0))
      })
      val filterlists=filterArticle(rateRDD,ArticleDF,x.getInt(0))
      filterlists.forEach(x=>{
        if(recAritclelist.contains(x))
          recAritclelist.remove(x)
      })
      redisTemplate.opsForValue().set("recommendlist"+x.getInt(0),recAritclelist)
    })
    println("冷启动推荐完成")
    println("推荐列表计算完成")
    rateRecently(spark)
    spark.stop()
    println("模型计算完成--"+LocalDateTime.now())
    val file : File= new File(PATH+"/checkpoint")
    if(file.exists() && file.list().length > 0){
      FileUtils.deleteDirectory(file);
    }
  }

  def computeRmse(model: MatrixFactorizationModel, realRatings: RDD[Rating]): Double = {
    val testingData = realRatings.map{ case Rating(user, product, rate) =>
      (user, product)
    }

    val prediction = model.predict(testingData).map{ case Rating(user, product, rate) =>
      ((user, product), rate)
    }

    val realPredict = realRatings.map{case Rating(user, product, rate) =>
      ((user, product), rate)
    }.join(prediction)

    sqrt(realPredict.map{ case ((user, product), (rate1, rate2)) =>
      val err = rate1 - rate2
      err * err
    }.mean())//mean = sum(list) / len(list)
  }

  def filterArticle(rateRDD: RDD[Rating],ArticleDF:DataFrame,user:Int): util.ArrayList[Integer] ={
    val myrateAidRDD = rateRDD.filter(x => x.user == user).map(x => {(x.product)})
    val myArticleRDD = ArticleDF.where(s"user_id=${user}").rdd.map(x => (x.getInt(0)))
    val mylist: util.ArrayList[Integer] = new util.ArrayList[Integer]()
    val myData = myrateAidRDD.intersection(myrateAidRDD).collect().foreach(x => {
      mylist.add(x)
    })
    return mylist
  }

  def rateRecently(spark:SparkSession):Unit={
    println("热门开始计算")

    //注册udf，将timesramp转化为年月日格式
    spark.udf.register("changeDate",(x:String)=>{
      val fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      val dt = fm.parse(x)
      val tim: Long = dt.getTime()
      val simpleDataFormat=new SimpleDateFormat("yyyyMM")
      val tm=simpleDataFormat.format(tim).toInt
      tm
    })

    //将原始rating数据转化为 aid，count,score,yearmonth
    val rateMoreRecentlyArtclesDF=spark
      .sql(s"SELECT aid,rates,changeDate(time) as yearmonth from SumRatings order by yearmonth desc,rates desc")
      .head(15)
    val rescRecentlyList: util.ArrayList[Integer]= new util.ArrayList[Integer]()
    rateMoreRecentlyArtclesDF.foreach(row =>{
      rescRecentlyList.add(row.getInt(0))
    })
    println(rescRecentlyList)
    redisTemplate.opsForValue().set("hotlist",rescRecentlyList)
    println("热门计算完成")
  }

}

