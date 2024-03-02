package com.zjr.assistant.mapper;

import com.zjr.assistant.entities.TypeTree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface TypeTreeMapper {
    List<TypeTree> getTypeTree(int id);
    List<TypeTree> findTypeByPid(int id);
    int addType(TypeTree typeTree);
}
