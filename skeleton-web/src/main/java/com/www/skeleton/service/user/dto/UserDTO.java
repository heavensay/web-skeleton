package com.www.skeleton.service.user.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @author lijianyu
 * @date 2019/3/19 16:03
 */
@Data
public class UserDTO {
    private Long id;

    @Length(max = 6,min = 2,message = "{hello.msg.length_50001}")
    private String userName;

    private String password;

    private Date updateTime;

    private Date createTime;
}
