package com.zjr.assistant.controller;

import com.github.pagehelper.PageInfo;
import com.zjr.assistant.service.serviceImpl.FollowServiceImpl;
import com.zjr.assistant.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class FollowController {
    @Autowired
    private FollowServiceImpl followService;

    @RequestMapping(value = "/isCare", method = RequestMethod.GET)
    public Result isCare(@RequestParam Map<String,Integer> map){
        if(followService.isCare(map)){
            return new Result(true,200,"");
        }else {
            return new Result(false,200,"");
        }
    }

    @RequestMapping(value = "/addCare", method = RequestMethod.POST)
    public Result addCare(@RequestParam Map<String, Integer> map){
        try {
            followService.addCare(map);
            return new Result(null,200,"关注成功");
        }catch (Exception e){
            return new Result(null,500,"服务器出错了");
        }
    }

    @RequestMapping(value = "/delCare", method = RequestMethod.GET)
    public Result delCare(@RequestParam Map<String, Integer> map){
        try {
            followService.delCare(map);
            return new Result(null,200,"取关成功");
        }catch (Exception e){
            return new Result(null,500,"服务器出错了");
        }
    }

    @GetMapping("/getCareAuthor/{id}")
    public Result getCareAuthor(@PathVariable("id") Integer id,Integer current, Integer pageSize){
        try {
            PageInfo careAuthor = followService.getCareAuthor(id, current, pageSize);
            return new Result(careAuthor,200,"获取成功");
        }catch (Exception e){
            System.out.println(e);
            return new Result(null,500,"获取失败");
        }
    }

}
