package com.www.skeleton.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.www.*")
public class SkeletonWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkeletonWebApplication.class, args);
	}

}

