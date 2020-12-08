package com.definesys.rt.bean;

import io.swagger.annotations.ApiParam;

import java.io.Serializable;

public class RegisterRequest implements Serializable {

    @ApiParam(value = "用户名",required = true)
    private String username;

    @ApiParam(value = "密码",required = true)
    private String password;

    @ApiParam(value = "用户姓名",required = true)
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
