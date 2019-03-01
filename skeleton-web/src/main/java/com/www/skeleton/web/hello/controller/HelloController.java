package com.www.skeleton.web.hello.controller;

import com.www.skeleton.service.hello.HelloService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author lijianyu
 * @date 2019/1/31 14:24
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/echoHelloWorld")
    @ResponseBody
    public String echoHelloWorld(String word){
        return "Hello world "+ Optional.ofNullable(word).orElse("");
    }

    @RequiresPermissions("getHappy")
    @GetMapping("/getHappy")
    @ResponseBody
    public String getHappy(){
        return helloService.getHappy();
    }
}
