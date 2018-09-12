package com.jonas.fastfood.controller.controllers;

import com.jonas.fastfood.commonservice.user.model.UserEntity;
import com.jonas.fastfood.controller.model.request.UserReq;
import com.jonas.fastfood.controller.utils.JsonResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Api(value="/UserController", tags="用户模块")
@RestController
@RequestMapping("/users")
public class UserController {

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={""}, method=RequestMethod.GET)
    public JsonResult getUserList() {
        List<UserEntity> userList = new ArrayList<UserEntity>();
        UserEntity user1 = new UserEntity("jj","111");
        UserEntity user2 = new UserEntity("js","222");
        UserEntity user3 = new UserEntity("jb","333");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return JsonResult.success(userList);
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    //@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.POST)
    public JsonResult postUser(@RequestBody UserReq user) {
        System.out.print(user.getUserName());
        System.out.print(user.getPassword());
        return JsonResult.success();
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public JsonResult getUser(@PathVariable Long id) {
        return JsonResult.success();
    }


    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public JsonResult putUser(@PathVariable Long id, @RequestBody UserReq user) {
        return JsonResult.success();
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public JsonResult deleteUser(@PathVariable Long id) {
        return JsonResult.success();
    }
}
