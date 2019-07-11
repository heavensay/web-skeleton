package com.www.skeleton.web.spring;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.www.skeleton.service.hello.data.DigitEnum;
import com.www.skeleton.service.hello.data.HelloEnum;
import com.www.skeleton.service.hello.SystemDictDataSourceCollect;
import com.www.skeleton.service.hello.data.CountryEnum;
import com.www.skeleton.util.dict.DictEnumSourceHelper;
import com.www.skeleton.util.dict.SysDictManager;
import com.www.skeleton.util.spring.JsonArgumentResolver;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validator;
import java.util.List;

/**
 * mvc自定义配置
 * 大部分配置已经由springboot默认配置完成
 * @author lijianyu
 * @date 2019/3/21 11:05
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer,ApplicationRunner {
    /**
     * 配置Validaiton，使用国际化配置文件来展示验证信息
     * @param messageSource
     * @return
     */
    @Bean
    public Validator validator(MessageSource messageSource){
        //使用国际化消息文件来返回验证信息
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

    /**
     * 支持跨域请求
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许cookies跨域
        config.addAllowedOrigin("*");// 允许向该服务器提交请求的URI，*表示全部允许。。这里尽量限制来源域，比如http://xxxx:8080 ,以降低安全风险。。
        config.addAllowedHeader("*");// 允许访问的头信息,*表示全部
        config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.addAllowedMethod("*");// 允许提交请求的方法，*表示全部允许，也可以单独设置GET、PUT等
    /*    config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");// 允许Get的请求方法
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");*/
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * 支持application/json body数据参数解析
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonArgumentResolver());
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        //日期格式化
//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // 3.在converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4.将converter赋值给HttpMessageConverter
        HttpMessageConverter<?> converter = fastConverter;
        // 5.返回HttpMessageConverters对象
        return new HttpMessageConverters(converter);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        initDictSources();
    }

    public void initDictSources(){
        SysDictManager.registerDictSource(new SystemDictDataSourceCollect());
        DictEnumSourceHelper.loadEnumSource(CountryEnum.class);
        DictEnumSourceHelper.loadEnumSource(HelloEnum.class);
        DictEnumSourceHelper.loadEnumSource(DigitEnum.class);
    }
}
