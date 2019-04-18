package com.www.skeleton.repository.po.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable{

    private Long id;

    private String userName;

    private String password;

    private String salt;

    private Date updateTime;

    private Date createTime;
}