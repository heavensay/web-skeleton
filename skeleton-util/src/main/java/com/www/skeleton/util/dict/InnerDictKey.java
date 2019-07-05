package com.www.skeleton.util.dict;

/**
 * class:User.class(建议表对应的实体class)
 * category:user(建议表名)
 * code:gender(建议表中字典字段)
 * value:man(字典值)
 * label:男(字典展示文本)
 */
public class InnerDictKey {

    private Class type;

    private String category;

    private String code;

    private String value;

    public InnerDictKey(Class type, String category, String code, String value) {
        this.type = type;
        this.category = category;
        this.code = code;
        this.value = value;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj instanceof InnerDictKey){
            InnerDictKey dkey = (InnerDictKey) obj;
            return type.equals(dkey.type)&& category.equals(dkey.category) && code.equals(dkey.code) && value.equals(dkey.value)  ;
        }
        return  false;
    }

    @Override
    public int hashCode() {
        String str = type+category+code+value;
        return str.hashCode();
    }
}
