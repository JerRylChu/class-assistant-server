package com.zjr.assistant.mapper;

import com.zjr.assistant.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginMapper {
    int register(@Param("user") User user);
    User getUserByPhoneNumber(String phoneNumber);
    User getUserInfo(Integer id);
    Integer getStars(Integer id);
    Integer getArticleCount(Integer id);
    Integer getFollowCount(Integer id);
    Integer updatePwd(User user);
    List<String> getImgPath();

}
