package com.zjr.assistant.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjr.assistant.entities.User;
import com.zjr.assistant.mapper.FollowMapper;
import com.zjr.assistant.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowMapper followMapper;

    @Override
    public boolean isCare(Map<String, Integer> map) {
        return followMapper.isCare(map)>0;
    }

    @Override
    public Integer addCare(Map<String, Integer> map) {
        return followMapper.addCare(map);
    }

    @Override
    public Integer delCare(Map<String, Integer> map) {
        return followMapper.delCare(map);
    }

    @Override
    public PageInfo getCareAuthor(Integer id, Integer current, Integer pageSize) {
        PageHelper.startPage(current,pageSize);
        List<User> careAuthor = followMapper.getCareAuthor(id);
        PageInfo<User> pageInfo = new PageInfo<>(careAuthor);
        return pageInfo;
    }

}
