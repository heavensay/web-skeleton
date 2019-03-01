package com.www.skeleton.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.www.*")
@MapperScan({"com.www.skeleton.repository.mapper","com.www.skeleton.repository.po"})
public class SkeletonWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkeletonWebApplication.class, args);
	}

}

