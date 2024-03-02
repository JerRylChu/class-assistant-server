package com.zjr.assistant.service;

import com.github.pagehelper.PageInfo;
import com.zjr.assistant.entities.Article;

import java.util.List;
import java.util.Map;

public interface CollectService {
    boolean isCollect(Map<String,Integer> map);
    Integer addCollect(Map<String,Integer> map);
    Integer delCollect(Map<String,Integer> map);
    PageInfo getCollectByUserId(Article article, Integer current, Integer pageSize);

}
