package com.www.skeleton.web.controller.hello;

import com.www.skeleton.repository.po.user.User;
import com.www.skeleton.service.common.exception.ServiceException;
import com.www.skeleton.service.hello.HelloService;
import com.www.skeleton.service.user.dto.UserDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

/**
 * @author lijianyu
 * @date 2019/1/31 14:24
 */
@Controller
@RequestMapping("/hello")
@Validated
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

    @GetMapping("/testValidtion")
    @ResponseBody
//    public String testValidtion(@Length(max = 6,min = 2,message = "{hello.msg.length}") String word){
    public String testValidtion(@NotNull String word){
        return "echo:"+ Optional.ofNullable(word).orElse("");
    }

    @GetMapping("/testValidtion2")
    @ResponseBody
    public String testValidtion2(@Length(max = 6,min = 2,message = "{hello.msg.length}") String word){
        return "echo:"+ Optional.ofNullable(word).orElse("");
    }

    @GetMapping("/testValidtionEntity")
    @ResponseBody
    public String testValidtionEntity(@Valid UserDTO user){
        return "echo:"+ Optional.ofNullable(user.getUserName()).orElse("");
    }

    @GetMapping("/testException")
    @ResponseBody
    public String testException(String msg){
//        throw new ServiceException(1,"hello.msg.echo");
        throw new ServiceException("hello.msg.echo_50000",msg);
    }
}
