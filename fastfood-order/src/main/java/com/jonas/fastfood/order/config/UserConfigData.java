package com.jonas.fastfood.order.config;

import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.resource.CollectResourceUtil;
import com.jonas.fastfood.common.resource.CollectTypeHandlerUtil;
import com.jonas.fastfood.common.utils.A;

import org.springframework.core.io.Resource;

/**
 * 用户模块的配置数据. 主要是 mybatis 的多配置目录和类型处理器
 */
final class UserConfigData {

    private static final String[] RESOURCE_PATH = new String[]{"mapps/*.xml", "mapps-custom/*.xml"};
    /**
     * 要加载的 mybatis 的配置文件目录
     */
    static final Resource[] RESOURCE_ARRAY = CollectResourceUtil.resource(A.maps(UserConfigData.class, RESOURCE_PATH));
}
