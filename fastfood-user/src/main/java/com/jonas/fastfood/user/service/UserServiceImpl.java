package com.jonas.fastfood.user.service;

import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.commonservice.user.UserService;
import com.jonas.fastfood.commonservice.user.model.LoginReq;
import com.jonas.fastfood.commonservice.user.model.UserEntity;
import com.alibaba.dubbo.config.annotation.Service;


@Service(version = Const.DUBBO_VERSION, timeout = Const.DUBBO_TIMEOUT, filter = Const.DUBBO_FILTER)
public class UserServiceImpl implements UserService {
    @Override
    public UserEntity login(LoginReq loginReq) {
        return new UserEntity("songjia","123");
    }
}
