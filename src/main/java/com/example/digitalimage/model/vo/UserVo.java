package com.example.digitalimage.model.vo;

import lombok.Data;

//@Data
public class UserVo {
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Integer userId;
    private String password;
}
