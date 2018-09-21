package com.jonas.fastfood.user.mapps;

import com.jonas.fastfood.commonservice.user.model.User;

public interface UserMapper {

    User selectByPrimaryKey(Long id);

    User selectByCondtion(User user);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}