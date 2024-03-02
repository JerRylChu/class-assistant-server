package com.zjr.assistant.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjr.assistant.entities.Article;
import com.zjr.assistant.entities.ArticleType;
import com.zjr.assistant.entities.Special;
import com.zjr.assistant.entities.Trail;
import com.zjr.assistant.service.ArticleService;
import com.zjr.assistant.service.serviceImpl.ArticleServiceImpl;
import com.zjr.assistant.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "文章相关")
@RestController
@RequestMapping("/article")
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 添加新文章
     * @param article
     * @return
     */
    @ApiOperation(value = "添加新文章")
    @PostMapping("/add")
    public Result add(@ApiParam(value = "文章内容",required = true) @RequestBody Article article){
        try {
            article.setCreateTime(new Date());
            boolean add = articleService.add(article);
            if(add){
                return new Result(null,200,"添加成功");
            }else {
                return new Result(null,500,"添加失败");
            }
        }catch (Exception e){
            System.out.println(e);
            return new Result(null,500,"添加失败");
        }
    }

    /**
     * 根据文章id获取文章信息
     * @param id 文章id
     * @return
     */
    @ApiOperation("根据文章id获取文章")
    @GetMapping("/getArticle/{id}")
    public Result getArticleById(@ApiParam(value = "文章id",required = true)@PathVariable(required = true) Integer id,
                                 @RequestParam(required = false) Integer userId){
        try {
            Article article = articleService.getArticleById(id);
            article.setStars(articleService.getStars(id));
            article.setReads(articleService.getReads(id));
            article.setComments(articleService.getComments(id));
            if(!Objects.isNull(userId) && userId != 0){
                Trail trail = new Trail(userId,id,new Date());
                if(articleService.isUserOwnArticle(trail)<1){
                    articleService.addTrail(trail);
                }
            }
            return new Result(article,200,"获取文章成功");
        }catch (Exception e){
            System.out.println(e);
            return new Result(null,500,"获取失败");
        }
    }

    @ApiOperation("获取用户的推荐文章")
    @RequestMapping("/getRecommendArticles/{userId}")
    public Result getRecommendArticles(@PathVariable("userId") Integer id,String pattern, int current, int pageSize){
        try {
            //普通查询
            if(!StringUtils.isEmpty(pattern)){
                PageInfo recommendArticles = articleService.getRecommendArticles(pattern, current, pageSize);
                return new Result(recommendArticles, 200, "获取成功");
            }else {
                if(id == 0){
                    PageInfo recommendArticles = articleService.getRecommendArticles(pattern, current, pageSize);
                    return new Result(recommendArticles, 200, "获取成功");
                }else {
                    String recommendlist = redisTemplate.opsForValue().get("recommendlist"+id);
                    ArrayList arrayList = JSONObject.parseObject(recommendlist, ArrayList.class);
                    ArrayList<Integer> arrayList1 = JSONObject.parseObject(arrayList.get(1).toString(), ArrayList.class);
                    PageInfo articlesByList = articleService.getArticlesByList(arrayList1, current, pageSize);
                    return new Result(articlesByList,200,"获取成功");
                }
            }
        }catch (Exception e){
            System.out.println(e);
            return new Result(null,500,"获取失败");
        }
    }

    @GetMapping("/getHotList")
    public Result getHotList(){
        try {
            String hotlist = redisTemplate.opsForValue().get("hotlist");
            ArrayList arrayList = JSONObject.parseObject(hotlist, ArrayList.class);
            ArrayList<Integer> arrayList1 = JSONObject.parseObject(arrayList.get(1).toString(), ArrayList.class);
            List<Article> hotList = articleService.getHotList(arrayList1);
            return new Result(hotList,200,"获取成功");
        }catch (Exception e){
            logger.error("获取热门出错，123行：",e);
            return new Result(null,500,"获取失败");
        }
    }

    @ApiOperation("获取用户写的文章")
    @RequestMapping("/getArticlesByUserId/{id}")
    public Object getArticlesByUserId(@ApiParam(value = "用户id", required = true)@PathVariable(required = true) Integer id,
                                      String title, Integer majorId, Integer current, Integer pageSize){
        try {
            Article article = new Article();
            article.setUserId(id);
            article.setTitle(title);
            article.setMajor(majorId);
            PageInfo articlesByUserId = articleService.getArticlesByUserId(article,current,pageSize);
            return new Result(articlesByUserId,200,"获取成功");
        }catch (Exception e){
            System.out.println(e);
            return new Result(null,500,"获取失败");
        }
    }

    @RequestMapping(value = "/delArticle",method = RequestMethod.GET)
    public Result delArticleById(Integer id){
        if(articleService.delArticleById(id)){
            return new Result(null,200,"删除成功");
        }else {
            return new Result(null,500,"删除失败");
        }
    }

    @RequestMapping(value = "/updateArticle",method = RequestMethod.POST)
    public Result updateArticleById(@RequestBody Article article){
        if(articleService.updateArticleById(article)){
            return new Result(null,200,"修改成功");
        }else {
            return new Result(null,500,"修改失败");
        }
    }

    @RequestMapping(value = "/getArticleType",method = RequestMethod.GET)
    public Result getArticleStatistic(Integer id){
        try {
            List<ArticleType> articleStatistic = articleService.getArticleStatistic(id);
            return new Result(articleStatistic,200,"获取成功");
        }catch (Exception e){
            return new Result(null,500,"获取失败");
        }
    }

    @GetMapping("/getSpecial")
    public Result getSpecialByUserId(@RequestParam Integer id){
        try {
            List<Special> specialByUserId = articleService.getSpecialByUserId(id);
            return new Result(specialByUserId,200,"");
        }catch (Exception e){
            System.out.println(e);
            return new Result(null,500,"获取失败");
        }
    }
}
