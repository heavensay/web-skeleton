package com.www.skeleton.web.controller.user;

import com.www.skeleton.repository.po.user.User;
import com.www.skeleton.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lijianyu
 * @date 2019/2/18 14:12
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listUser")
    @ResponseBody
    public List<User> listUser(){
        return userService.listUser();
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(String username,String password,Boolean rememberMe) throws Exception{
        User user = userService.queryUserByName(username);
        Subject subject = SecurityUtils.getSubject();

        if(rememberMe == null){
            rememberMe = false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
//        token.setRememberMe(true);
        subject.login(token);

        return username;
    }

}
