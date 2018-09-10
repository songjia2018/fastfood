package com.jonas.fastfood.commonservice.user;

import com.jonas.fastfood.commonservice.user.model.LoginReq;
import com.jonas.fastfood.commonservice.user.model.UserEntity;

public interface UserService {

    UserEntity login(LoginReq loginReq);
}
