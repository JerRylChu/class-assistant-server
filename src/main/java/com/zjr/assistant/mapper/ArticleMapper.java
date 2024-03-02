package com.zjr.assistant.mapper;

import com.zjr.assistant.entities.Article;
import com.zjr.assistant.entities.ArticleType;
import com.zjr.assistant.entities.Special;
import com.zjr.assistant.entities.Trail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {
    int add(Article article);
    Article getArticleById(Integer id);
    List<Article> getArticlesByUserId(Article article);
    List<Article> getRecommendArticles(String pattern);
    int updateArticleById(Article article);
    int delArticleById(Integer id);
    List<ArticleType> getArticleStatistic(Integer id);
    Integer getStars(Integer id);
    Integer getReads(Integer id);
    Integer getComments(Integer id);
    Integer addTrail(Trail trail);
    List<Special> getSpecialByUserId(Integer id);
    List<Article> getArticlesByList(List list);
    Integer isUserOwnArticle(Trail trail);
}
