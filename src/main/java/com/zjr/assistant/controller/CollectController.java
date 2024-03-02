package com.zjr.assistant.controller;

import com.github.pagehelper.PageInfo;
import com.zjr.assistant.entities.Article;
import com.zjr.assistant.service.serviceImpl.CollectServiceImpl;
import com.zjr.assistant.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CollectController {
    @Autowired
    private CollectServiceImpl collectService;

    @RequestMapping(value = "/isCollect",method = RequestMethod.GET)
    public Result isCollect(@RequestParam Map<String,Integer> map){
        if(collectService.isCollect(map)){
            return new Result(true,200,"");
        }else {
            return new Result(false,200,"");
        }
    }

    @RequestMapping(value = "/addCollect",method = RequestMethod.POST)
    public Result addCollect(@RequestParam Map<String,Integer> map){
        try {
            collectService.addCollect(map);
            return new Result(null,200,"收藏成功");
        }catch (Exception e){
            System.out.println(e);
            return new Result(null,500,"收藏失败");
        }
    }

    @RequestMapping(value = "/delCollect",method = RequestMethod.GET)
    public Result delCollect(@RequestParam Map<String,Integer> map){
        try {
            collectService.delCollect(map);
            return new Result(null,200,"取消收藏成功");
        }catch (Exception e){
            return new Result(null,500,"取消收藏失败");
        }
    }

    @GetMapping("/getCollectByUserId/{id}")
    public Result getCollectByUserId(@PathVariable("id") Integer id,String title, Integer majorId, Integer current, Integer pageSize){
        try {
            Article article = new Article();
            article.setUserId(id);
            article.setTitle(title);
            article.setMajor(majorId);
            PageInfo collectByUserId = collectService.getCollectByUserId(article, current, pageSize);
            return new Result(collectByUserId,200,"获取成功");
        }catch (Exception e){
            System.out.println(e);
            return new Result(null,500,"获取失败");
        }
    }

}
