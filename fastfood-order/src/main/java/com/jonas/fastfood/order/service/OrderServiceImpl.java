package com.jonas.fastfood.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.model.order.Order;
import com.jonas.fastfood.common.service.order.OrderService;
import com.jonas.fastfood.order.mapps.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName OrderServiceImpl
 * @Description TOD
 * @Author songjia
 * @Date 2018/12/1713:46
 * @Verstion 1.0
 **/
@Service(version = Const.DUBBO_VERSION, timeout = Const.DUBBO_TIMEOUT, filter = Const.DUBBO_FILTER, interfaceName = "com.jonas.fastfood.common.service.order.OrderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public int save(Order order) {
        return orderMapper.insertSelective(order);
    }

    @Override
    public int update(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }
}
