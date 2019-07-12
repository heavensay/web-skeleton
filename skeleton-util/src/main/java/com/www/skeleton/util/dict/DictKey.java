package com.www.skeleton.util.dict;

import com.www.skeleton.util.Asserts;

/**
 * class:User.class(建议表对应的实体class)
 * category:user(建议表名)
 * code:gender(建议表中字典字段)
 * value:man(字典值)
 * label:男(字典展示文本)
 */
public class DictKey {

    private Class type;

    private String category;

    private String code;

    private Object value;

    public DictKey(String category, String code, String value) {
        this(DefaultType.class,category,code,value);
    }

    public DictKey(Class type, String category, String code, Object value) {
        Asserts.notNull(type,"type不能为空");
        Asserts.notNull(category,"category不能为空");
        Asserts.notNull(code,"code不能为空");
        Asserts.notNull(value,"value不能为空");

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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj instanceof DictKey){
            DictKey dkey = (DictKey) obj;
            return type.equals(dkey.type)&& category.equals(dkey.category) && code.equals(dkey.code) && value.equals(dkey.value)  ;
        }
        return  false;
    }

    @Override
    public int hashCode() {
        String str = type+category+code+value.hashCode();
        return str.hashCode();
    }
}
