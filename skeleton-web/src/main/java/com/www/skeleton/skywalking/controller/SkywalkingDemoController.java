package com.www.skeleton.skywalking.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author lijianyu
 * @date 2026/4/23 14:04
 */
@Controller
@RequestMapping("/skywalking")
@Validated
@Slf4j
public class SkywalkingDemoController {

    @GetMapping("/echoTrace")
    @ResponseBody
    public String echoHelloWorld(String word){
        log.debug(word);
        return "Hello skywalking "+ Optional.ofNullable(word).orElse("");
    }

    /*自定义tag测试*/
    @GetMapping("/tag")
    @ResponseBody
    public String customTag(String word){
        // 手动增加 Tag
        ActiveSpan.tag("custom.tag", "test-tag");
        return "Hello skywalking2 "+ Optional.ofNullable(word).orElse("");
    }

    /*模拟慢请求，测试sw trace profile功能*/
    @GetMapping("/profile")
    @ResponseBody
    public String profileTest(String word) throws InterruptedException {
        // 手动增加 Tag
        Thread.sleep(5000L);
        return "profileTest:"+ LocalDateTime.now();
    }
}
