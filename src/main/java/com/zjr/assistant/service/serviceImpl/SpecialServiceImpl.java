package com.zjr.assistant.service.serviceImpl;

import com.zjr.assistant.entities.Special;
import com.zjr.assistant.mapper.SpecialMapper;
import com.zjr.assistant.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialServiceImpl implements SpecialService {
    @Autowired
    private SpecialMapper specialMapper;

    @Override
    public List<Special> getSpecial() {
        return specialMapper.getSpecial();
    }
}
