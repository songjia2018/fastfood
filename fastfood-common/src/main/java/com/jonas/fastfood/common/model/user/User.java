package com.jonas.fastfood.common.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description TOD
 * @Author songjia
 * @Date 2018/12/1715:31
 * @Verstion 1.0
 **/
public class User implements Serializable {

    private Long id;
    private String userName;
    private String userPwd;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
