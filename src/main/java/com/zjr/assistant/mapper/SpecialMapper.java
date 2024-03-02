package com.zjr.assistant.mapper;

import com.zjr.assistant.entities.Special;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SpecialMapper {
    List<Special> getSpecial();
}
