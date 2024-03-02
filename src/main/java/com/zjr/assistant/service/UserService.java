package com.zjr.assistant.service;

import com.zjr.assistant.entities.User;

public interface UserService {
    boolean updateSignature(User user);
    boolean updateAvatar(User user);
}
