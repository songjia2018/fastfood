package com.jonas.fastfood.user.service;

import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.commonservice.user.UserService;
import com.jonas.fastfood.commonservice.user.model.User;
import com.alibaba.dubbo.config.annotation.Service;
import com.jonas.fastfood.user.mapps.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Service(version = Const.DUBBO_VERSION, timeout = Const.DUBBO_TIMEOUT, filter = Const.DUBBO_FILTER)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
       return userMapper.selectByCondtion(user);
    }
}
