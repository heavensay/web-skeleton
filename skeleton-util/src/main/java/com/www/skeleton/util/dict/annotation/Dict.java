package com.www.skeleton.util.dict.annotation;

import com.www.skeleton.util.dict.DefaultType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 提供pojo上需要翻译的属性的元数据
 * /**
 * class:User.class(建议表对应的实体class)
 * category:user(建议表名)
 * code:gender(建议表中字典字段)
 * value:man(字典值)
 * label:男(字典展示文本)
 *
 * @author ljy
 * @date 2019/7/3 17:51
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {

    Class type() default DefaultType.class;

    String category() default DictConfiguration.DEFAULT_CATEGORY;

    String code() default DictConfiguration.DEFAULT_CODE;

    String valueColumn();
}
