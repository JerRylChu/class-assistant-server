package com.zjr.assistant.controller;

import com.zjr.assistant.entities.Special;
import com.zjr.assistant.service.serviceImpl.SpecialServiceImpl;
import com.zjr.assistant.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "大学专业分类相关")
@RequestMapping("/special")
@RestController
public class SpecialController {
    @Autowired
    private SpecialServiceImpl specialService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("获取专业分类")
    @RequestMapping("/getSpecial")
    public Result getSpecial(){
        if(redisTemplate.hasKey("specials")){
            Object specials = redisTemplate.opsForValue().get("specials");
            return new Result(specials,200,"");
        }else {
            try {
                List<Special> special = specialService.getSpecial();
                redisTemplate.opsForValue().set("specials",special);
                return new Result(special,200,"");
            }catch (Exception e){
                return new Result(null,500,"获取专业失败");
            }
        }

    }
}
