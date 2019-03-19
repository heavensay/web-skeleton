package com.www.skeleton.web.common.controller;

import com.www.skeleton.web.common.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lijianyu
 * @date 2019/1/28 15:39
 */
@Slf4j
@Controller
public class ExceptionController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    private ErrorAttributes errorAttributes;

    @Autowired
    public ExceptionController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(ERROR_PATH)
    @ResponseBody
    public ApiResponse errorApiHandler(HttpServletRequest request, HttpServletResponse resp,WebRequest req) throws Exception {
        Throwable throwable = errorAttributes.getError(req);

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        log.error("ExceptionController:{},{},{}=",request.getPathInfo(), statusCode,throwable);

        String exceptionMessage = throwable == null? null:throwable.getMessage();
        ApiResponse apiResponse = new ApiResponse(-1,exceptionMessage);

//        if (ex instanceof AuthException) {
//            apiResponse.setErrorMessage(ex.getMessage());
//            apiResponse.setCode(((BusinessException) ex).getCode());
//            resp.setStatus(HttpStatus.SC_FORBIDDEN);
//        }else if(ex instanceof BusinessException){
//            apiResponse.setErrorMessage(ex.getMessage());
//            apiResponse.setCode(((BusinessException) ex).getCode());
//        }else{
//            apiResponse.setErrorMessage(CcbcMessage.getMessage(CcbcMessage.C500_UNKOWN_SYSTEM_ERROR));
//            apiResponse.setCode(CcbcMessage.C500_UNKOWN_SYSTEM_ERROR);
//        }
//        if(statusCode == 401){
//            return "/401"
//        }else if(statusCode == 404){
//            return "/404"
//        }else if(statusCode == 403){
//            return "/403"
//        }else{
//            return "/500"
//        }
        return apiResponse;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
