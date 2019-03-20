package com.jonas.fastfood.controller.controllers;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jonas.fastfood.common.annotation.NeedLogin;
import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.model.user.User;
import com.jonas.fastfood.common.mvc.BackendSessionUtil;
import com.jonas.fastfood.common.utils.json.JsonResult;
import com.jonas.fastfood.common.service.user.UserService;

import com.jonas.fastfood.controller.config.BackendWebBean;
import com.jonas.fastfood.controller.model.request.UserReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@NeedLogin
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private BackendWebBean backendWebBean;

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private UserService userService;

    @NeedLogin(flag = false)
    @ApiOperation(value = "用户登录", notes = "简单的输入用户名和密码")
    @PostMapping(value = "login")
    public JsonResult login(UserReq rquestUser) {
        User user = new User();
        user.setUserName(rquestUser.getUserName());
        user.setUserPwd(rquestUser.getPassword());
        user = userService.login(user);
        // 登录成功之后
        // User user = xxx;
        String loginSign = backendWebBean.addLoginSignInCache(user.getId());
        String token = BackendSessionUtil.whenLogin(user, loginSign);
        //user.setToken(token);
        return JsonResult.success(user);
    }

    @ApiOperation(value = "获取用户列表", notes = "")
    @GetMapping
    public JsonResult getUserList() {
        return JsonResult.success();
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @PostMapping
    public JsonResult postUser(UserReq user) {
        System.out.print(user.getUserName());
        System.out.print(user.getPassword());
        return JsonResult.success();
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping(value = "/{id}")
    public JsonResult getUser(@PathVariable Long id) {
        return JsonResult.success();
    }


    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @PutMapping(value = "/{id}")
    public JsonResult putUser(@PathVariable Long id, UserReq user) {
        return JsonResult.success();
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}")
    public JsonResult deleteUser(@PathVariable Long id) {
        return JsonResult.success();
    }
}
