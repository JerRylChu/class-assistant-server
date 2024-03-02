package com.zjr.assistant.service.serviceImpl;

import com.zjr.assistant.mapper.StarMapper;
import com.zjr.assistant.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StarServiceImpl implements StarService {
    @Autowired
    private StarMapper starMapper;


    @Override
    public boolean isStar(Map<String, Integer> map) {
        return starMapper.isStar(map)>0;
    }

    @Override
    public Integer addStar(Map<String, Integer> map) {
        return starMapper.addStar(map);
    }

    @Override
    public Integer delStar(Map<String, Integer> map) {
        return starMapper.delStar(map);
    }
}
