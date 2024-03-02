package com.zjr.assistant.mapper;

import com.zjr.assistant.entities.Article;

import java.util.List;
import java.util.Map;

public interface CollectMapper {
    Integer isCollect(Map<String,Integer> map);
    Integer addCollect(Map<String,Integer> map);
    Integer delCollect(Map<String,Integer> map);
    List<Article> getCollectByUserId(Article article);
}
