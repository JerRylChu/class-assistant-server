package com.zjr.assistant.controller;

import com.zjr.assistant.entities.User;
import com.zjr.assistant.service.serviceImpl.UserServiceImpl;
import com.zjr.assistant.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户相关")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @ApiOperation("修改个性签名")
    @RequestMapping("/updateSignature")
    public Result updateSignature(@RequestBody @ApiParam(value = "用户信息",required = true) User user){
        if(userService.updateSignature(user)){
            return new Result(user,200,"修改成功");
        }
        return new Result(null,500,"修改失败");
    }

}
