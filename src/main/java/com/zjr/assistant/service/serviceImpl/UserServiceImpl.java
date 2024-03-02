package com.zjr.assistant.service.serviceImpl;

import com.zjr.assistant.entities.User;
import com.zjr.assistant.mapper.UserMapper;
import com.zjr.assistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean updateSignature(User user) {
        return userMapper.updateSignature(user) > 0;
    }

    @Override
    public boolean updateAvatar(User user) {
        return userMapper.updateAvatar(user)>0;
    }
}
