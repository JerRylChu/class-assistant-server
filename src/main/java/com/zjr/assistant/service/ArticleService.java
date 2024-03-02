package com.zjr.assistant.service;

import com.github.pagehelper.PageInfo;
import com.zjr.assistant.entities.Article;
import com.zjr.assistant.entities.ArticleType;
import com.zjr.assistant.entities.Special;
import com.zjr.assistant.entities.Trail;

import java.util.List;

public interface ArticleService {
    boolean add(Article article);
    Article getArticleById(Integer id);
    PageInfo getArticlesByUserId(Article article, int current, int pageSize);
    PageInfo getRecommendArticles(String pattern, int current, int pageSize);
    boolean updateArticleById(Article article);
    boolean delArticleById(Integer id);
    List<ArticleType> getArticleStatistic(Integer id);
    Integer getStars(Integer id);
    Integer getReads(Integer id);
    Integer getComments(Integer id);
    Integer addTrail(Trail trail);
    List<Special> getSpecialByUserId(Integer id);
    PageInfo getArticlesByList(List list, int current, int pageSize);
    Integer isUserOwnArticle(Trail trail);

}
