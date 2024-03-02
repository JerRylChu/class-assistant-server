package com.zjr.assistant.mapper;

import java.util.Map;

public interface StarMapper {
    Integer isStar(Map<String,Integer> map);
    Integer addStar(Map<String,Integer> map);
    Integer delStar(Map<String,Integer> map);
}
