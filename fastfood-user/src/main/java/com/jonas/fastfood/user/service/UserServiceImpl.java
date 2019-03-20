package com.jonas.fastfood.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.model.order.Order;
import com.jonas.fastfood.common.model.product.Product;
import com.jonas.fastfood.common.model.user.Account;
import com.jonas.fastfood.common.model.user.User;
import com.jonas.fastfood.common.service.order.OrderService;
import com.jonas.fastfood.common.service.product.ProductService;
import com.jonas.fastfood.common.utils.DateUtil;
import com.jonas.fastfood.common.service.user.UserService;

import com.alibaba.dubbo.config.annotation.Service;

import com.jonas.fastfood.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service(version = Const.DUBBO_VERSION, timeout = Const.DUBBO_TIMEOUT, filter = Const.DUBBO_FILTER, interfaceName = "com.jonas.fastfood.common.service.user.UserService")
public class UserServiceImpl implements UserService {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private ProductService productService;
    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private OrderService orderService;


    @Autowired
    private UserMapper userMapper;


}
