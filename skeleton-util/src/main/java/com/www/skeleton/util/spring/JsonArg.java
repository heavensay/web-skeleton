package com.www.skeleton.util.spring;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * json路径，支持阿里fastjson jsonpath路径格式
 * e.g. $.name;$.user.name
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface JsonArg {

	@AliasFor("value")
	String path() default "";

	@AliasFor("path")
	String value() default "";
}