package com.jonas.fastfood.commonservice.user.model;


import java.io.Serializable;

public class UserEntity implements Serializable {

    private String userName;
    private String password;

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
