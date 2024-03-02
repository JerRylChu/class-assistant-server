package com.zjr.assistant.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjr.assistant.entities.Article;
import com.zjr.assistant.mapper.CollectMapper;
import com.zjr.assistant.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean isCollect(Map<String, Integer> map) {
        return collectMapper.isCollect(map) > 0;
    }

    @Override
    public Integer addCollect(Map<String, Integer> map) {
        return collectMapper.addCollect(map);
    }

    @Override
    public Integer delCollect(Map<String, Integer> map) {
        return collectMapper.delCollect(map);
    }

    @Override
    public PageInfo getCollectByUserId(Article article, Integer current, Integer pageSize) {
        PageHelper.startPage(current,pageSize);
        List<Article> collectByUserId = collectMapper.getCollectByUserId(article);
        PageInfo<Article> pageInfo = new PageInfo<>(collectByUserId);
        return pageInfo;
    }
}
