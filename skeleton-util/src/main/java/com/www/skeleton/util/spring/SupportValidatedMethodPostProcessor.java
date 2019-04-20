package com.www.skeleton.util.spring;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 支持对spring @validated验证；在service和dao层都可以进行验证
 * @author ljy
 * @date 2019/4/20 12:06
 */
public class SupportValidatedMethodPostProcessor extends MethodValidationPostProcessor {

    @Nullable
    private Validator validator;

    /**
     * Set the JSR-303 Validator to delegate to for validating methods.
     * <p>Default is the default ValidatorFactory's default Validator.
     */
    @Override
    public void setValidator(Validator validator) {
        // Unwrap to the native Validator with forExecutables support
        if (validator instanceof LocalValidatorFactoryBean) {
            this.validator = ((LocalValidatorFactoryBean) validator).getValidator();
        }
        else if (validator instanceof SpringValidatorAdapter) {
            this.validator = validator.unwrap(Validator.class);
        }
        else {
            this.validator = validator;
        }
    }

    /**
     * Set the JSR-303 ValidatorFactory to delegate to for validating methods,
     * using its default Validator.
     * <p>Default is the default ValidatorFactory's default Validator.
     * @see javax.validation.ValidatorFactory#getValidator()
     */
    @Override
    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validator = validatorFactory.getValidator();
    }

    @Override
    public void afterPropertiesSet() {
        Pointcut pointcut = new AnnotationMatchingPointcut(Validated.class,true);
        //声明一个aspectj切点
        AspectJExpressionPointcut cut = new AspectJExpressionPointcut();

        //设置需要拦截的方法-用切点语言来写
//        cut.setExpression("execution(public * com.www.skeleton.service..*.*(..))");//拦截:空参返回值为int的run方法
        this.advisor = new DefaultPointcutAdvisor(pointcut, createMethodValidationAdvice(this.validator));
    }

    @Override
    protected Advice createMethodValidationAdvice(@Nullable Validator validator) {
        return (validator != null ? new SupportValidatedMethodInterceptor(validator) : new SupportValidatedMethodInterceptor());
    }
}
