package com.www.skeleton.util.spring.bodyadvice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ljy
 * @date 2019/7/3 17:51
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictKey {

    Class type() default Object.class;

    String category() default "default";

    String code();

    String valueColumn();
}
