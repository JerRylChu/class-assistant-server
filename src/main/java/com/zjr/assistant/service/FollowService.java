package com.zjr.assistant.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface FollowService {
    boolean isCare(Map<String,Integer> map);
    Integer addCare(Map<String,Integer> map);
    Integer delCare(Map<String,Integer> map);
    PageInfo getCareAuthor(Integer id,Integer current, Integer pageSize);

}
