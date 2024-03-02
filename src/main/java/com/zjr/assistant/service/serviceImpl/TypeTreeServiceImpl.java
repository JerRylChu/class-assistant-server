package com.zjr.assistant.service.serviceImpl;

import com.zjr.assistant.entities.TypeTree;
import com.zjr.assistant.mapper.TypeTreeMapper;
import com.zjr.assistant.service.TypeTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeTreeServiceImpl implements TypeTreeService {
    @Autowired
    private TypeTreeMapper typeTreeMapper;

    @Override
    public List<TypeTree> getTypeTree(int id) {
        List<TypeTree> typeTree = typeTreeMapper.getTypeTree(id);
        return typeTree;
    }

    @Override
    public List<TypeTree> findTypeByPid(int id) {
        return typeTreeMapper.findTypeByPid(id);
    }

    @Override
    public boolean addType(TypeTree typeTree) {
        return typeTreeMapper.addType(typeTree)>0;
    }
}
