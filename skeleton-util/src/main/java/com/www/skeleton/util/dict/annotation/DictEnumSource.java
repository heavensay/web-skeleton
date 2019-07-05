package com.www.skeleton.util.dict.annotation;

/**
 * @author ljy
 * @date 2019/7/5 18:52
 */
public @interface DictEnumSource {

    Class type();

    String category() default "";

    String codeFieldName();

    String labelFieldName();
}
