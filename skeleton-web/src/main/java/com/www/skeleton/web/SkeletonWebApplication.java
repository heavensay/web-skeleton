package com.www.skeleton.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
@ComponentScan(basePackages = "com.www.*")
@MapperScan({"com.www.skeleton.repository.mapper","com.www.skeleton.repository.po"})
public class SkeletonWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkeletonWebApplication.class, args);
	}

	/**
	 * 方法中Valid验证支持
	 * @return
	 */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
}

