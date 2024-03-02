package com.zjr.assistant.mapper;

import com.zjr.assistant.entities.User;

public interface UserMapper {
    int updateSignature(User user);
    int updateAvatar(User user);
}
