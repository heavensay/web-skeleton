package com.www.skeleton.dubbo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.DemoService;
import org.apache.dubbo.springboot.demo.idl.Greeter;
import org.apache.dubbo.springboot.demo.idl.GreeterReply;
import org.apache.dubbo.springboot.demo.idl.GreeterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static java.awt.SystemColor.info;

/**
 * @author lijianyu
 * @date 2026/04/22 08:34
 */
@Controller
@RequestMapping("/dubboidl")
@Validated
public class DubboIDLController {

    @DubboReference(check = false)
    private Greeter greeter;

    @GetMapping("/echoDubbo")
    @ResponseBody
    public String echoDubbo(String word){
        return "dubbo request "+ Optional.ofNullable(word).orElse("");
    }
    @GetMapping("/sayHello")
    @ResponseBody
    public String sayHello(String word){
        GreeterReply result = greeter.greet(GreeterRequest.newBuilder().setName("name").build());
        return "dubbo idl response:"+ Optional.ofNullable(result.getMessage()).orElse("");
    }
}
