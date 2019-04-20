package com.www.skeleton.web.spring;

import com.www.skeleton.util.spring.SupportValidatedMethodPostProcessor;
import org.hibernate.validator.internal.engine.ConfigurationImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validator;

/**
 * mvc自定义配置
 * 大部分配置已经由springboot默认配置完成
 * @author lijianyu
 * @date 2019/3/21 11:05
 */
@Configuration
public class MvcConfiguration {
    /**
     * 配置Validaiton，使用国际化配置文件来展示验证信息
     * @param messageSource
     * @return
     */
    @Bean
    public Validator validator(MessageSource messageSource){
        //使用国际化消息文件来返回验证信息
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean(){
            @Override
            protected void postProcessConfiguration(javax.validation.Configuration<?> configuration) {
                ((ConfigurationImpl)configuration).allowOverridingMethodAlterParameterConstraint(true);
            }
        };
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

    @Bean
    public SupportValidatedMethodPostProcessor supportValidatedMethodPostProcessor(Validator validator){
        SupportValidatedMethodPostProcessor supportValidatedMethodPostProcessor = new SupportValidatedMethodPostProcessor();
        supportValidatedMethodPostProcessor.setValidator(validator);

        return supportValidatedMethodPostProcessor;
    }
}
