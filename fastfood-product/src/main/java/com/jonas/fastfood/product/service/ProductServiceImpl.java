package com.jonas.fastfood.product.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.model.product.Product;
import com.jonas.fastfood.common.service.product.ProductService;
import com.jonas.fastfood.product.mapps.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ProductServiceImpl
 * @Description TOD
 * @Author songjia
 * @Date 2018/12/1711:16
 * @Verstion 1.0
 **/
@Service(version = Const.DUBBO_VERSION, timeout = Const.DUBBO_TIMEOUT, filter = Const.DUBBO_FILTER, interfaceName = "com.jonas.fastfood.common.service.product.ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional
    public int save(Product product) {
        productMapper.insertSelective(product);
        return product.getId();
    }

    @Override
    public int update(Product product) {
        return productMapper.updateByPrimaryKeySelective(product);
    }
}
