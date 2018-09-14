package com.jonas.fastfood.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.utils.json.JsonResult;
import com.jonas.fastfood.commonservice.user.UserService;
import com.jonas.fastfood.web.model.request.AdminReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value="adminController", tags="管理员模块")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private UserService userService;

    @ApiOperation(value="用户登录",notes="简单的输入用户名和密码")
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public JsonResult login(@RequestBody AdminReq user){
        return JsonResult.success(user);
    }
}
