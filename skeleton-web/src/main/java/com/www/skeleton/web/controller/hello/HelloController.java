package com.www.skeleton.web.controller.hello;

import com.www.skeleton.service.common.exception.ServiceException;
import com.www.skeleton.service.hello.HelloService;
import com.www.skeleton.service.user.dto.UserDTO;
import com.www.skeleton.util.i18n.LocaleMessageService;
import com.www.skeleton.util.spring.JsonArg;
import com.www.skeleton.util.spring.SpringContextHolder;
import com.www.skeleton.SkeletonWebApplication;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;
import java.util.List;
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
        Object b = SpringContextHolder.getBean(SqlSessionFactory.class);

        return "Hello world "+ Optional.ofNullable(word).orElse("");
    }

    @GetMapping("/queryHelloBean")
    @ResponseBody
    public HelloBean queryHelloBean(){
        HelloBean helloBean = new HelloBean();
        helloBean.setName("ddddd");
        helloBean.setGentleman("lady");
        return helloBean;
    }

    /*********权限*************/
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
    @RequiresPermissions("getHappy2")
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
    /*********权限*************/


    /**********validation*************/
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
    public String testValidtionEntity(@Valid UserDTO user){
        return "echo:"+ Optional.ofNullable(user.getUserName()).orElse("");
    }
    /**********validation*************/


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

    @GetMapping("/html")
    public String staticHtml(){
        return "hello";
    }


    /** json body参数解析测试**/
    @PostMapping(value = "/jsonParamOne",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String jsonParamOne(@RequestBody String userName){
        return userName;
    }

    @PostMapping(value = "/jsonParamSimple",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String jsonParamSimple(@JsonArg("userName") String userName){
        return userName;
    }

    @PostMapping(value = "/jsonParamObject",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String jsonParamObject(@JsonArg("$") UserDTO userDTO){
        return userDTO.toString();
    }

    @PostMapping(value = "/jsonParamList",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String jsonParamList(@JsonArg("$") List<String> list){
        return StringUtils.join(list,",");
    }

    @PostMapping(value = "/jsonParamArray",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String jsonParamArray(@JsonArg("$") String[] strs){
        return StringUtils.join(strs,",");
    }

    @PostMapping(value = "/jsonParamListNoGeneric",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String jsonParamListNoGeneric(@JsonArg("$") List list){
        return StringUtils.join(list,",");
    }
    /** json body参数解析测试**/

    @GetMapping(value = "/dictAutoConvert")
    @ResponseBody
    public HelloBean dictAutoConvert(){
        String locationPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        System.out.println("locationPath=========="+locationPath);
        try {
            System.out.println("locationPath2=========="+ ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HelloBean helloBean = new HelloBean();
        helloBean.setName("ddddd");
        helloBean.setGentleman("lady");
        helloBean.setAnimalName("DOG");
        helloBean.setDigit(1);
        helloBean.setCountry("zh");
        return helloBean;
    }

    @PostMapping(value = "/xwwwformUrlencoded", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String xwwwformUrlencoded(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "age", required = false) Integer age,
                                     @RequestParam(value = "gender", required = false) String gender) {
        return String.format("name:%s,age:%d,gender:%s", name, age, gender);
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String upload(@RequestParam(value = "file",required = false) MultipartFile file,
                         @RequestParam(value = "msg",required = false) String msg){
        return "msg:"+msg+"upload:"+file.getName()+",size:"+file.getSize()+",origin file name:"+file.getOriginalFilename();
    }
}
