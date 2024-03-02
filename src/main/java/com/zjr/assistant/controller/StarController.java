package com.zjr.assistant.controller;

import com.zjr.assistant.service.serviceImpl.StarServiceImpl;
import com.zjr.assistant.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StarController {
    @Autowired
    private StarServiceImpl starService;

    @RequestMapping(value = "/isStar",method = RequestMethod.GET)
    public Result isStar(@RequestParam Map<String,Integer> map){
        if(starService.isStar(map)){
            return new Result(true,200,"");
        }else {
            return new Result(false,200,"");
        }
    }

    @RequestMapping(value = "/addStar",method = RequestMethod.POST)
    public Result addStar(@RequestParam Map<String,Integer> map){
        try {
            starService.addStar(map);
            return new Result(null,200,"");
        }catch (Exception e){
            return new Result(null,500,"服务器出错了");
        }
    }

    @RequestMapping(value = "/delStar",method = RequestMethod.GET)
    public Result delStar(@RequestParam Map<String,Integer> map){
        try {
            starService.delStar(map);
            return new Result(null,200,"");
        }catch (Exception e){
            return new Result(null,500,"服务器出错了");
        }
    }
}
