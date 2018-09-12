package com.jonas.fastfood.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户")
public class UserReq {

    @ApiModelProperty(name="userName",value = "用户名称")
    private String userName;
    @ApiModelProperty(name="password",value = "密码")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
