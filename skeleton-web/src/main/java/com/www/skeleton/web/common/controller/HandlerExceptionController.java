package com.www.skeleton.web.common.controller;

import com.www.skeleton.service.common.exception.ServiceException;
import com.www.skeleton.util.i18n.LocaleMessageService;
import com.www.skeleton.util.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * app异常统一处理，同时处理Controller层异常和404、500错误信息；
 * @author lijianyu
 * @date 2019/1/28 15:39
 */
@Slf4j
@Controller
public class HandlerExceptionController implements ErrorController {

    @Autowired
    private LocaleMessageService localeMessageService;

    private static final String ERROR_PATH = "/error";

    private ErrorAttributes errorAttributes;

    @Autowired
    public HandlerExceptionController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(ERROR_PATH)
    @ResponseBody
    public ApiResponse errorApiHandler(HttpServletRequest request, HttpServletResponse resp,WebRequest req) throws Exception {
        Throwable throwable = errorAttributes.getError(req);
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        ApiResponse apiResponse = null;

        if(throwable != null){
           apiResponse = buildExceptionInfo(throwable,resp);
        }else if(statusCode != null){
            apiResponse = buildHttpStatusError(statusCode);
        }else{
            apiResponse = new ApiResponse(5002,"系统错误");
        }
        apiResponse.setStatus(resp.getStatus());

        log.error("request invoke error,request.javax.servlet.error.status_code:{},response.status:{},exception info:",
                statusCode,resp.getStatus(),throwable);

        return apiResponse;
    }

    private ApiResponse buildExceptionInfo(Throwable  t,HttpServletResponse response){
        ApiResponse apiResponse = new ApiResponse();

        if(t instanceof Exception){
            Exception ex = (Exception) t;
            //spring @valid注解的方法没绑定BindingResult参数，则检查失败抛出BindException
            if(ex instanceof ServiceException){
                apiResponse.setCode(((ServiceException) ex).getCode());
                apiResponse.setMessage(ex.getMessage());
            }else if(ex instanceof ServletException && ex.getCause() instanceof ServiceException){
                //Servlet Filter规范doFilter抛出异常ServletException，包装原始异常；
                //这里也对Filter中的异常进行统一捕获
                apiResponse.setCode(((ServiceException) ex.getCause()).getCode());
                apiResponse.setMessage(((ServiceException) ex.getCause()).getMessage());
            } else if(ex instanceof BindException){
                StringBuilder sb = new StringBuilder();
                ((BindException) ex).getAllErrors().stream().forEach(e->{
                    sb.append(e.getDefaultMessage()+",");
                });
                apiResponse.setMessage(StringUtils.substringBeforeLast(sb.toString(),","));
            }else if(ex instanceof ConstraintViolationException){
                //valid验证异常处理
                StringBuilder sb = new StringBuilder();
                ((ConstraintViolationException) ex).getConstraintViolations().stream().forEach(e->{
                    sb.append(e.getMessage()+",");
                });
                apiResponse.setMessage(StringUtils.substringBeforeLast(sb.toString(),","));
            }else if(ex instanceof ShiroException){
                //权限异常处理
                handlerPermsException(apiResponse,ex,response);
            }else{
                apiResponse.setCode(10001);
                apiResponse.setMessage(localeMessageService.getMessage("system.unknow.error_10001"));
            }
            apiResponse.setDetail(ex.toString());
        }
        return apiResponse;
    }

    /**
     * 权限统一异常处理，注意针对权限异常，我们设置HttpServletResponse.status=401
     *
     * * 授权异常
     * <p>
     * org.apache.shiro.authz.UnauthenticatedException  授权异常
     * org.apache.shiro.authz.HostUnauthorizedException 没有访问权限
     * org.apache.shiro.authz.UnauthorizedException     没有访问权限
     * org.apache.shiro.authz.AuthorizationException    上面异常的父类
     * <p>
     *
     * 认证异常
     * <p>
     * org.apache.shiro.authc.pam.UnsupportedTokenException 身份令牌异常，不支持的身份令牌
     * org.apache.shiro.authc.UnknownAccountException       未知账户/没找到帐号,登录失败
     * org.apache.shiro.authc.LockedAccountException        帐号锁定
     * org.apache.shiro.authz.DisabledAccountException      用户禁用
     * org.apache.shiro.authc.ExcessiveAttemptsException    登录重试次数，超限。只允许在一段时间内允许有一定数量的认证尝试
     * org.apache.shiro.authc.ConcurrentAccessException     一个用户多次登录异常：不允许多次登录，只能登录一次 。即不允许多处登录
     * org.apache.shiro.authz.AccountException              账户异常
     * org.apache.shiro.authz.ExpiredCredentialsException   过期的凭据异常
     * org.apache.shiro.authc.IncorrectCredentialsException 错误的凭据异常
     * org.apache.shiro.authc.CredentialsException          凭据异常
     * org.apache.shiro.authc.AuthenticationException       上面异常的父类
     *
     * @param ex 没有权限的异常
     * @return ModelAndView
     */
    private void handlerPermsException(ApiResponse apiResponse,Exception ex,HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if(ex instanceof AuthenticationException){
            apiResponse.setCode(10101);
            apiResponse.setMessage(localeMessageService.getMessage("system.perms.authentication_10101"));
        }else if(ex instanceof UnauthenticatedException){
            apiResponse.setCode(10101);
            apiResponse.setMessage(localeMessageService.getMessage("system.perms.authentication_10101"));
        }else if(ex instanceof AuthorizationException){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            apiResponse.setCode(10102);
            //用户没有操作权限
            apiResponse.setMessage(localeMessageService.getMessage("system.perms.authentication_10102"));
        }else {
            apiResponse.setCode(10100);
            //用户没有操作权限
            apiResponse.setMessage(localeMessageService.getMessage("system.perms.authentication_10100"));
        }
    }

    private ApiResponse buildHttpStatusError(Integer statusCode){
        ApiResponse apiResponse = new ApiResponse(5001,"系统错误");
        if(statusCode == 401){
            apiResponse.setCode(statusCode);
        }else if(statusCode == 404){
            apiResponse.setCode(statusCode);
        }else if(statusCode == 403){
            apiResponse.setCode(statusCode);
        }else{
            apiResponse.setCode(statusCode);
        }
        return apiResponse;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
