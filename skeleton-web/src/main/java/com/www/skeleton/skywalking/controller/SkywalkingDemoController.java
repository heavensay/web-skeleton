package com.www.skeleton.skywalking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
