package com.zjr.assistant.mapper;

import com.zjr.assistant.entities.User;
import org.apache.spark.ml.source.libsvm.LibSVMDataSource;

import java.util.List;
import java.util.Map;

public interface FollowMapper {
    Integer isCare(Map<String,Integer> map);
    Integer addCare(Map<String,Integer> map);
    Integer delCare(Map<String,Integer> map);
    List<User> getCareAuthor(Integer id);
}
