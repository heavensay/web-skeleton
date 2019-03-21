package com.www.skeleton.util.spring;

import com.alibaba.fastjson.JSON;
import com.www.skeleton.util.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lijianyu
 * @date 2019/1/28 21:34
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    /**
     * Whether this component supports the given controller method return type
     * and the selected {@code HttpMessageConverter} type.
     *
     * @param returnType    the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked;
     * {@code false} otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //判断支持的类型，因为我们定义的BaseResponseVo 里面的data可能是任何类型，这里就不判断统一放过
        //如果你想对执行的返回体进行操作，可将上方的Object换成你自己的类型
        return true;
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.debug("请求返回数据{}", body);

        ApiResponse result = new ApiResponse();
        result.setData(body);
        result.setStatus(HttpServletResponse.SC_OK);

        if(body instanceof ApiResponse){
            result = (ApiResponse) body;
        }else if (body instanceof String) {
            //因为spring handler处理类的返回类型是String，为了保证一致性，这里需要将ResponseResult转回去
            //处理返回值是String的情况
            return JSON.toJSONString(result);
        }else{
            result.setData(body);
        }
        return result;
    }
}