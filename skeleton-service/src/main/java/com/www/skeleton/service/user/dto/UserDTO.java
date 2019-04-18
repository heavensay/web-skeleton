package com.www.skeleton.service.user.dto;

import com.www.skeleton.service.user.dto.validation.ValidateAddUser;
import com.www.skeleton.service.user.dto.validation.ValidateUpdateUser;
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

    @Length(max = 3,min = 1,message = "{hello.msg.length_50001}",groups = ValidateAddUser.class)
    @Length(max = 6,min = 4,message = "{hello.msg.length_50001}",groups = ValidateUpdateUser.class)
    private String userName;

    private String password;

    private Date updateTime;

    private Date createTime;
}
