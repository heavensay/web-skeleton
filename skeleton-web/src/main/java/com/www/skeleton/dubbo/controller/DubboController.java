package com.www.skeleton.dubbo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author lijianyu
 * @date 2026/04/22 08:34
 */
@Controller
@RequestMapping("/dubbo")
@Validated
public class DubboController {

    //启动不检查
    @DubboReference(check = false)
    private DemoService demoService;

    @GetMapping("/echoDubbo")
    @ResponseBody
    public String echoDubbo(String word){
        return "dubbo request "+ Optional.ofNullable(word).orElse("");
    }
    @GetMapping("/sayHello")
    @ResponseBody
    public String sayHello(String word){
        String info = demoService.sayHello(word);
        return "dubbo response:"+ Optional.ofNullable(demoService.sayHello(info)).orElse("");
    }
}
