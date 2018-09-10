package com.jonas.fastfood.user.service;

import com.jonas.fastfood.commonservice.user.UserService;
import com.jonas.fastfood.commonservice.user.model.LoginReq;
import com.jonas.fastfood.commonservice.user.model.UserEntity;

public class UserServiceImpl implements UserService {
    @Override
    public UserEntity login(LoginReq loginReq) {
        return new UserEntity("songjia","123");
    }
}
