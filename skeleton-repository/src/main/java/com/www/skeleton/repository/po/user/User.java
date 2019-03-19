package com.www.skeleton.repository.po.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;

    private String userName;

    private String password;

    private Date updateTime;

    private Date createTime;
}