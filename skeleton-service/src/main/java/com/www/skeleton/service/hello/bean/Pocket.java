package com.www.skeleton.service.hello.bean;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author ljy
 * @date 2019/4/19 21:03
 */
@Data
public class Pocket {

    @NotNull(message = "口袋名不能为空")
    private String name;

    @Valid
    @NotNull
    private Wallet wallet;

}
