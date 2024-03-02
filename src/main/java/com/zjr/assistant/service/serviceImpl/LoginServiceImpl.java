package com.zjr.assistant.service.serviceImpl;

import com.google.code.kaptcha.Producer;
import com.zjr.assistant.entities.User;
import com.zjr.assistant.mapper.LoginMapper;
import com.zjr.assistant.service.LoginService;
import com.zjr.assistant.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private Producer kaptchaProducer;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void getKaptcha(HttpServletResponse response, String timestamp) {
        // 生成验证码
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);
        redisTemplate.opsForValue().set(timestamp,text,60, TimeUnit.SECONDS);
        System.out.println("验证码----------------------------------"+text);
        // 将图片输出给浏览器
        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            System.out.println("响应验证码失败:" + e.getMessage());
        }
    }

    @Override
    public boolean register(User user) {
        user.setCreateTime(new Date());
        user.setHeadIconUrl("https://img2.baidu.com/it/u=3717120934,3932520698&fm=26&fmt=auto&gp=0.jpg");
        return loginMapper.register(user)>0;
    }

    @Override
    public boolean userIsExist(String PhoneNumber) {
        return loginMapper.getUserByPhoneNumber(PhoneNumber) != null;
    }

    @Override
    public User getUserByPhoneNumber(String PhoneNumber) {
        return loginMapper.getUserByPhoneNumber(PhoneNumber);
    }

    @Override
    public User getUserInfo(Integer id) {
        User userInfo = loginMapper.getUserInfo(id);
        userInfo.setPassword("");
        return userInfo;
    }

    @Override
    public Integer getStars(Integer id) {
        return loginMapper.getStars(id);
    }

    @Override
    public Integer getArticleCount(Integer id) {
        return loginMapper.getArticleCount(id);
    }

    @Override
    public Integer getFollowCount(Integer id) {
        return loginMapper.getFollowCount(id);
    }

    @Override
    public Integer updatePwd(User user) {
        return loginMapper.updatePwd(user);
    }

    @Override
    public List<String> getImgPath() {
        return loginMapper.getImgPath();
    }


}
