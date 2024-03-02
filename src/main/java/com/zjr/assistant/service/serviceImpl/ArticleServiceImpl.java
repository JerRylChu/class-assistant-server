package com.zjr.assistant.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjr.assistant.entities.Article;
import com.zjr.assistant.entities.ArticleType;
import com.zjr.assistant.entities.Special;
import com.zjr.assistant.entities.Trail;
import com.zjr.assistant.mapper.ArticleMapper;
import com.zjr.assistant.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean add(Article article) {
        return articleMapper.add(article)>0;
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public PageInfo getArticlesByUserId(Article article, int current, int pageSize) {
        PageHelper.startPage(current,pageSize);
        List<Article> articlesByUserId = articleMapper.getArticlesByUserId(article);
        for(Article article1: articlesByUserId){
            article1.setStars(articleMapper.getStars(article1.getId()));
            article1.setReads(articleMapper.getReads(article1.getId()));
            article1.setComments(articleMapper.getComments(article1.getId()));
        }
        PageInfo<Article> pageInfo = new PageInfo<>(articlesByUserId);
        return pageInfo;
    }

    @Override
    public PageInfo getRecommendArticles(String pattern, int current, int pageSize) {
        PageHelper.startPage(current,pageSize);
        List<Article> recommendArticles = articleMapper.getRecommendArticles(pattern);
        for (Article article: recommendArticles) {
            article.setStars(articleMapper.getStars(article.getId()));
            article.setReads(articleMapper.getReads(article.getId()));
            article.setComments(articleMapper.getComments(article.getId()));
        }
        PageInfo<Article> pageInfo = new PageInfo<>(recommendArticles);
        return pageInfo;
    }

    @Override
    public boolean updateArticleById(Article article) {
        return articleMapper.updateArticleById(article)>0;
    }

    @Override
    public boolean delArticleById(Integer id) {
        return articleMapper.delArticleById(id)>0;
    }

    @Override
    public List<ArticleType> getArticleStatistic(Integer id) {
        return articleMapper.getArticleStatistic(id);
    }

    @Override
    public Integer getStars(Integer id) {
        return articleMapper.getStars(id);
    }

    @Override
    public Integer getReads(Integer id) {
        return articleMapper.getReads(id);
    }

    @Override
    public Integer getComments(Integer id) {
        return articleMapper.getComments(id);
    }

    @Override
    public Integer addTrail(Trail trail) {
        return articleMapper.addTrail(trail);
    }

    @Override
    public List<Special> getSpecialByUserId(Integer id) {
        return articleMapper.getSpecialByUserId(id);
    }

    @Override
    public PageInfo getArticlesByList(List list, int current, int pageSize) {
        PageHelper.startPage(current,pageSize);
        List<Article> hotArticles = articleMapper.getArticlesByList(list);
        for (Article article: hotArticles) {
            article.setStars(articleMapper.getStars(article.getId()));
            article.setReads(articleMapper.getReads(article.getId()));
            article.setComments(articleMapper.getComments(article.getId()));
        }
        PageInfo<Article> pageInfo = new PageInfo<>(hotArticles);
        return pageInfo;
    }

    @Override
    public Integer isUserOwnArticle(Trail trail) {
        return articleMapper.isUserOwnArticle(trail);
    }

    public List<Article> getHotList(List list){
        return articleMapper.getArticlesByList(list);
    }

}
