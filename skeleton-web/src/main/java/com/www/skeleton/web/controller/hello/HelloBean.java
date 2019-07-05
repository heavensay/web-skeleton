package com.www.skeleton.web.controller.hello;

import com.www.skeleton.util.spring.bodyadvice.DictKey;
import lombok.Data;

/**
 * @author ljy
 * @date 2019/7/4 9:47
 */
@Data
public class HelloBean {

    private String name;

    private String gentleman;

    @DictKey(code = "gender",valueColumn = "gentleman")
    private String gentlemanText;

}
