package com.www.skeleton.util.spring.bodyadvice;

import com.www.skeleton.util.dict.DictBeanIntrospect;
import com.www.skeleton.util.dict.SysDictManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 针对Controller返回值，进行字典自动转换
 * @author lijianyu
 * @date 2019/1/28 21:34
 */
@RestControllerAdvice
//@DependsOn("globalResponseHandler")
public class DictResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    Logger logger = LoggerFactory.getLogger(DictResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return DictBeanIntrospect.acquireDictMetadata(returnType.getParameterType()).size()>0;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        List<DictBeanIntrospect.DictMetadata> list = DictBeanIntrospect.acquireDictMetadata(returnType.getParameterType());
        for (DictBeanIntrospect.DictMetadata dm : list) {
            SysDictManager.assignValueLabel(dm,body);
        }
        return body;
    }
}