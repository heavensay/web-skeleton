package com.www.skeleton.service.hello.data;

import com.helix.dict.annotation.DictEnumSource;

/**
 * @author ljy
 * @date 2019/7/5 18:27
 */
@DictEnumSource(valueLabelFieldName = "text",valueFieldName = "code")
public enum DigitEnum {

    A(1,"一"),
    B(2,"二"),
    ;

    private Integer code;
    private String text;

    DigitEnum(Integer code, String text){
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
