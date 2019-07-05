package com.www.skeleton.service.hello.data;

/**
 * @author ljy
 * @date 2019/7/5 18:27
 */
public enum HelloEnum {

    A("zh","中国"),
    B("am","美国"),
    ;

    private String code;
    private String text;

    HelloEnum(String code,String text){
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
