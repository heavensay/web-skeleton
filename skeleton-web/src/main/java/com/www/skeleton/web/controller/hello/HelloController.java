package com.www.skeleton.web.controller.hello;

import com.www.skeleton.service.common.exception.ServiceException;
import com.www.skeleton.service.hello.HelloService;
import com.www.skeleton.service.user.dto.UserDTO;
import com.www.skeleton.service.user.dto.validation.ValidateAddUser;
import com.www.skeleton.service.user.dto.validation.ValidateUpdateUser;
import com.www.skeleton.util.i18n.LocaleMessageService;
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

    @Autowired
    private LocaleMessageService localeMessageService;

    @GetMapping("/echoHelloWorld")
    @ResponseBody
    public String echoHelloWorld(String word){
        return "Hello world "+ Optional.ofNullable(word).orElse("");
    }

    /**
     * 测试需要用户拥有权限并且登录过，才能访问此资源；既shiro配置此url->authc
     * @return
     */
    @RequiresPermissions("getHappy")
    @GetMapping("/needPerms")
    @ResponseBody
    public String needPerms(){
        return helloService.getHappy();
    }

    /**
     * 只需要记住登录并且拥有getHappy权限才能登录
     * @return
     */
    @RequiresPermissions("getHappy")
    @GetMapping("/needRememberme")
    @ResponseBody
    public String needRememberme(){
        return helloService.getHappy();
    }

    /**
     * 只需要记住登录并且拥有getHappy权限才能登录
     * @return
     */
    @RequiresPermissions("getHappy222")
    @GetMapping("/noPermission")
    @ResponseBody
    public String noPermission(){
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
    public String testValidtion2(@Length(max = 6,min = 2,message = "{hello.msg.length_50001}") String word){
        return "echo:"+ Optional.ofNullable(word).orElse("");
    }

    @GetMapping("/testValidtionEntity")
    @ResponseBody
    public String testValidtionEntity(@Validated(value = ValidateUpdateUser.class) UserDTO user){
        return "echo:"+ Optional.ofNullable(user.getUserName()).orElse("");
    }

    @GetMapping("/testValidtionEntityGroup")
    @ResponseBody
    public String testValidtionEntityGroup(@Validated(value = ValidateAddUser.class) UserDTO user){
        return "echo:"+ Optional.ofNullable(user.getUserName()).orElse("");
    }

    @GetMapping("/testException")
    @ResponseBody
    public String testException(String msg){
//        throw new ServiceException(1,"hello.msg.echo");
        throw new ServiceException("hello.msg.echo_50000",msg);
    }

    @GetMapping("/i18n")
    @ResponseBody
    public String i18n(){
        return localeMessageService.getMessage("system.normal_10000");
    }

}
