package com.www.skeleton.util.spring;

import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.util.TypeUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 支持application/json body数据参数解析
 */
public class JsonArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String JSONBODYATTRIBUTE = "JSON_REQUEST_BODY";
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonArg.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String body = getRequestBody(webRequest);

        String arg = parameter.getParameterAnnotation(JsonArg.class).path();
        if (StringUtils.isEmpty(arg)) {
        	arg = parameter.getParameterName();
        }

        Object result = JSONPath.extract(body,arg);

        return TypeUtils.cast(result,parameter.getGenericParameterType(),null);
    }

    private String getRequestBody(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        try {
            BufferedReader bufferedReader = servletRequest.getReader();
            StringBuilder builder = new StringBuilder();

            int size = 1024;
            int readNum = 0;
            char[] cs = new char[size];
            while ((readNum = bufferedReader.read(cs, 0, size)) != -1) {
                builder.append(cs, 0, readNum);
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("request json body获取失败",e);
        }
    }
}