package com.jonas.fastfood.common.service.product;

import com.jonas.fastfood.common.model.product.Product;

/**
 * @ClassName ProductService
 * @Description TOD
 * @Author songjia
 * @Date 2018/12/1711:14
 * @Verstion 1.0
 **/
public interface ProductService {

    int save(Product product);

    int update(Product product);
}
