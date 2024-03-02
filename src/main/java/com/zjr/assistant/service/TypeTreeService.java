package com.zjr.assistant.service;

import com.zjr.assistant.entities.TypeTree;

import java.util.List;

public interface TypeTreeService {
    List<TypeTree> getTypeTree(int id);
    List<TypeTree> findTypeByPid(int id);
    boolean addType(TypeTree typeTree);
}
