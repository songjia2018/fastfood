package com.jonas.fastfood.controller.controllers;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jonas.fastfood.common.annotation.NeedLogin;
import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.model.product.Product;
import com.jonas.fastfood.common.model.user.User;
import com.jonas.fastfood.common.utils.json.JsonResult;
import com.jonas.fastfood.common.service.user.UserService;

import com.jonas.fastfood.controller.model.request.TansReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TransactionTestController
 * @Description TOD
 * @Author songjia
 * @Date 2018/12/1714:05
 * @Verstion 1.0
 **/
@NeedLogin
@Api(tags = "事务测试")
@RestController
@RequestMapping("/transaction/")
public class TransactionTestController {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private UserService userService;


    @NeedLogin(flag = false)
    @ApiOperation(value = "测试", notes = "测试事务回滚")
    @PostMapping(value = "test")
    public JsonResult test(TansReq tansReq) {
        User user = new User();
        user.setId(Long.valueOf(tansReq.getUserId()));

        Product product = new Product();
        product.setPrdName(tansReq.getProductName());
        product.setDiscreb(tansReq.getDescrib());
        product.setPrice(tansReq.getPrice());
        product.setProductNum(tansReq.getProductNum());

        try {
            userService.transaction(user, product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.success(user);
    }
}
