package com.www.skeleton.util.spring.bodyadvice;

import com.alibaba.fastjson.JSON;
import com.www.skeleton.util.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * Controller返回值统一包装为ApiResponse，进行返回
 * @author lijianyu
 * @date 2019/1/28 21:34
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //判断支持的类型，因为我们定义的BaseResponseVo 里面的data可能是任何类型，这里就不判断统一放过
        //如果你想对执行的返回体进行操作，可将上方的Object换成你自己的类型
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ApiResponse result = new ApiResponse();
        result.setData(body);
        result.setStatus(HttpServletResponse.SC_OK);

        if(ApiResponse.class.isAssignableFrom(returnType.getParameterType())){
            result = (ApiResponse) body;
        }/*else if (String.class.isAssignableFrom(returnType.getParameterType())) {
            //因为spring handler处理类的返回类型是String，为了保证一致性，这里需要将ResponseResult转回去
            //处理返回值是String的情况
            return JSON.toJSONString(result);
        }*/else{
            result.setData(body);
        }
        return result;
    }
}