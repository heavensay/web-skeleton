package com.www.skeleton.util.dict.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ljy
 * @date 2019/7/5 18:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictEnumSource {

    String category() default "";

    /**
     * @return true:代表value为Enum名称,valueFieldName()属性则无效;
     */
    boolean valueFieldIsEnumName() default true;

    String valueFieldName() default "";

    String valueLabelFieldName();
}
