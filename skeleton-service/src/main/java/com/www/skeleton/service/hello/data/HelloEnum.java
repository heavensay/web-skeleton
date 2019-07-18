package com.www.skeleton.service.hello.data;


import com.helix.dict.annotation.EnumDictSourceMetadata;

/**
 * @author ljy
 * @date 2019/7/9 20:06
 */
@EnumDictSourceMetadata(valueLabelFieldName = "text")
public enum HelloEnum {
    A("你好"),
    B("hello world"),
    ;

    private String text;
    HelloEnum(String text){
        this.text = text;
    }

}
