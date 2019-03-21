package com.www.skeleton.web.common.controller;

import com.www.skeleton.service.common.exception.ServiceException;
import com.www.skeleton.util.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * @author lijianyu
 * @date 2019/1/28 15:39
 */
@Slf4j
@Controller
public class HandlerExceptionController implements ErrorController {

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
        log.error("ExceptionController:{},{},{}=",request.getPathInfo(), statusCode,throwable);

        ApiResponse apiResponse = null;

        if(throwable != null){
           apiResponse = buildExceptionInfo(throwable);
        }else if(statusCode != null){
            apiResponse = buildHttpStatusError(statusCode);
        }else{
            apiResponse = new ApiResponse(5002,"系统错误哦");
        }
        apiResponse.setStatus(statusCode);

        return apiResponse;
    }

    private ApiResponse buildExceptionInfo(Throwable  t){
        ApiResponse apiResponse = new ApiResponse();

        if(t instanceof Exception){
            Exception ex = (Exception) t;
            //spring @valid注解的方法没绑定BindingResult参数，则检查失败抛出BindException
            if(ex instanceof ServiceException){
                apiResponse.setCode(((ServiceException) ex).getCode());
                apiResponse.setMessage(ex.getMessage());
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
            }else{
                apiResponse.setCode(500);
                apiResponse.setMessage("系统错误");
            }
            apiResponse.setDetail(ex.toString());
        }
        return apiResponse;
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
