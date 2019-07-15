package com.www.skeleton.service.hello.data;


import com.helix.dict.annotation.DictEnumSource;

/**
 * @author ljy
 * @date 2019/7/5 18:27
 */
@DictEnumSource(valueLabelFieldName = "text",valueFieldName = "code")
public enum CountryEnum {

    A("zh","中国"),
    B("am","美国"),
    ;

    private String code;
    private String text;

    CountryEnum(String code, String text){
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
