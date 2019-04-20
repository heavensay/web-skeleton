package com.www.skeleton.service.hello.bean;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author ljy
 * @date 2019/4/19 21:01
 */
@Data
public class Wallet {

    @NotNull(message = "钱包名不能为空")
    private String name;

    @Min(value = 100)
    private Integer account;

}
