package com.zjr.assistant.controller;

import com.zjr.assistant.entities.User;
import com.zjr.assistant.service.serviceImpl.LoginServiceImpl;
import com.zjr.assistant.utils.Md5Util;
import com.zjr.assistant.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Api(tags = "登陆相关")
@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation("验证码图片")
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public void getKaptcha(HttpServletResponse response, @ApiParam(value = "时间戳（存储验证码）",required = true) String timestamp) {
        loginService.getKaptcha(response,timestamp);
    }

    @ApiOperation("注册新用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody @ApiParam(value = "新用户信息",required = true) User user){
        if(loginService.userIsExist(user.getPhoneNumber())){
            return new Result(null,300,"该手机号已被注册");
        }
        user.setPassword(Md5Util.getString(user.getPassword()));
        if(loginService.register(user)){
            return new Result(null,200,"注册成功");
        }else {
            return new Result(null,500,"注册失败");
        }
    }

    @ApiOperation("登录（web端）")
    @RequestMapping(value = "/doLogin/{time}", method = RequestMethod.POST)
    public Result doLogin(@RequestBody @ApiParam(value = "用户输入的账号密码以及验证码") User user,
                          @PathVariable(required = true) @ApiParam(value = "时间戳（提取验证码）") String time){
        if(redisTemplate.opsForValue().get(time) == null){
            return new Result(null,301,"验证码已过期");
        }
        if(!Objects.requireNonNull(redisTemplate.opsForValue().get(time)).equalsIgnoreCase(user.getConfirmCode())){
            redisTemplate.delete(time);
            return new Result(null,310,"验证码错误");
        }
        redisTemplate.delete(time);
        if(!loginService.userIsExist(user.getPhoneNumber())){
            return new Result(null,300,"用户不存在");
        }
        User userByUsername = loginService.getUserByPhoneNumber(user.getPhoneNumber());
        if(userByUsername.getPassword().equals(Md5Util.getString(user.getPassword()))){
            userByUsername.setPassword("");
            return new Result(userByUsername,200,"登陆成功");
        }else {
            return new Result(null,500,"密码错误");
        }
    }

    @ApiOperation("根据用户id获取用户信息")
    @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET)
    public Result getUserInfo(@PathVariable(required = true) @ApiParam(value = "用户id",required = true) Integer id){
        User userInfo = loginService.getUserInfo(id);
        return new Result(userInfo,200,"");
    }

    @ApiOperation("根据用户id获取个人主页相关信息")
    @RequestMapping(value = "/getHomeInfo/{id}", method = RequestMethod.GET)
    public Result getHomeInfo(@PathVariable(required = true) @ApiParam(value = "用户id", required = true) Integer id){
        HashMap<String,Integer> map = new HashMap<>();
        Integer stars = loginService.getStars(id);
        Integer articles = loginService.getArticleCount(id);
        Integer follows = loginService.getFollowCount(id);
        map.put("stars",stars);
        map.put("articles",articles);
        map.put("follows",follows);
        return new Result(map,200,"");
    }

    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody User user){
        try {
            user.setPassword(Md5Util.getString(user.getPassword()));
            loginService.updatePwd(user);
            return new Result(null,200,"修改成功");
        }catch (Exception e){
            logger.error("修改密码错误：",e);
            return new Result(null,500,"修改失败");
        }
    }
}
