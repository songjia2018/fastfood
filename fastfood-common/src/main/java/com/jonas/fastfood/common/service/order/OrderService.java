package com.jonas.fastfood.common.service.order;

import com.jonas.fastfood.common.model.order.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

public interface OrderService {

    int save(Order order);

    int update(Order order);
}
