package com.zjr.assistant.service;

import com.zjr.assistant.entities.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface LoginService {
    void getKaptcha(HttpServletResponse response,String timestamp);
    boolean register(User user);
    boolean userIsExist(String phoneNumber);
    User getUserByPhoneNumber(String PhoneNumber);
    User getUserInfo(Integer id);
    Integer getStars(Integer id);
    Integer getArticleCount(Integer id);
    Integer getFollowCount(Integer id);
    Integer updatePwd(User user);
    List<String> getImgPath();
}
