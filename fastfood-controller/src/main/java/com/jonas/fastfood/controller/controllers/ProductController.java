package com.jonas.fastfood.controller.controllers;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jonas.fastfood.common.annotation.NeedLogin;
import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.model.product.Product;
import com.jonas.fastfood.common.service.product.ProductService;
import com.jonas.fastfood.common.utils.json.JsonResult;
import com.jonas.fastfood.controller.model.request.ProductReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ProductController
 * @Description TOD
 * @Author songjia
 * @Date 2018/12/1711:18
 * @Verstion 1.0
 **/
@NeedLogin
@Api(tags = "商品模块")
@RestController
@RequestMapping("/product/")
public class ProductController {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private ProductService productService;


    @ApiOperation(value = "更新产品信息", notes = "根据url的id来指定更新对象")
    @PostMapping(value = "modify")
    public JsonResult modify(ProductReq productReq) {
        try {
            Product product = new Product();
            product.setId(productReq.getProductId());
            product.setPrdName(productReq.getProductName());
            product.setDiscreb(productReq.getDiscreb());
            productService.update(product);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("失败了");
        }
        return JsonResult.success();
    }
}
