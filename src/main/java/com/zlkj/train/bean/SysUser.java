package com.zlkj.train.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class SysUser {
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

}