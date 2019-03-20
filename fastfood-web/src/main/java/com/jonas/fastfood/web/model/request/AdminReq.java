package com.jonas.fastfood.web.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "管理员")
public class AdminReq {

    @ApiModelProperty(name = "adminId", value = "管理员唯一ID")
    private int adminId;
    @ApiModelProperty(name = "userName", value = "用户名称")
    private String userName;
    @ApiModelProperty(name = "password", value = "密码")
    private String password;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

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
