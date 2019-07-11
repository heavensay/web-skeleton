package com.www.skeleton.service.hello;

import com.www.skeleton.util.dict.annotation.DictEnumSource;

/**
 * @author ljy
 * @date 2019/7/9 20:06
 */
@DictEnumSource(valueLabelFieldName = "text")
public enum CountryEnum {
    A("你好"),
    B("hello world"),
    ;

    private String text;
    CountryEnum(String text){
        this.text = text;
    }

}
