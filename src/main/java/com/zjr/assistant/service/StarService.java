package com.zjr.assistant.service;

import java.util.Map;

public interface StarService {
    boolean isStar(Map<String,Integer> map);
    Integer addStar(Map<String,Integer> map);
    Integer delStar(Map<String,Integer> map);
}
