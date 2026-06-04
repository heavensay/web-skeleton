package com.www.skeleton.otel.controller;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Scope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

/**
 * @author lijianyu
 * @date 2026/5/31 08:22
 */
@Controller
@RequestMapping("/otel")
@Validated
@Slf4j
public class OtelController {

    private final Random random = new Random();

    @GetMapping("/otelThrowException")
    @ResponseBody
    public String otelThrowException(String word){
        // nextDouble() 返回 [0.0, 1.0) 之间的双精度浮点数，乘以 100 映射到 0-100 范围
        double x = random.nextDouble()*100;

        //每10次请求，抛出一次异常
        if(x < 10){
            RuntimeException e = new RuntimeException("otel test error x:" + x);
            log.error("otel测试请求异常",e);
            throw e;
        }

        log.info("otel请求正常");
        return "otelThrowException "+ Optional.ofNullable(word).orElse("");
    }


    @GetMapping("/otelSpan")
    @ResponseBody
    public String otelSpan(String word){
        Runnable runnable = () -> {
            log.info("A slf4j log message with a span "+Optional.ofNullable(word).orElse(""));
        };

        Span span = GlobalOpenTelemetry.getTracer(OtelController.class.getName()+" trace").spanBuilder("otelSpan").startSpan();
        try (Scope unused = span.makeCurrent()) {
            runnable.run();
        } finally {
            span.end();
        }

        return "otelSpan "+ Optional.ofNullable(word).orElse("");
    }
}
