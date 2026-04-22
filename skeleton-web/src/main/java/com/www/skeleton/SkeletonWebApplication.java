package com.www.skeleton;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.www.skeleton.*")
@MapperScan({"com.www.skeleton.*.repository.mapper","com.www.skeleton.*.repository.po"})
@EnableDubbo
public class SkeletonWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkeletonWebApplication.class, args);
	}
}

