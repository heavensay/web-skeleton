package com.www.skeleton.util.spring;

import lombok.extern.java.Log;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;

import javax.validation.*;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author ljy
 * @date 2019/4/20 12:14
 */
@Log
public class SupportValidatedMethodInterceptor extends MethodValidationInterceptor {

    private final Validator validator;

    /**
     * Create a new MethodValidationInterceptor using a default JSR-303 validator underneath.
     */
    public SupportValidatedMethodInterceptor() {
        this(Validation.buildDefaultValidatorFactory());
    }

    /**
     * Create a new MethodValidationInterceptor using the given JSR-303 ValidatorFactory.
     * @param validatorFactory the JSR-303 ValidatorFactory to use
     */
    public SupportValidatedMethodInterceptor(ValidatorFactory validatorFactory) {
        this(validatorFactory.getValidator());
    }

    public SupportValidatedMethodInterceptor(Validator validator) {
        this.validator = validator;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // Avoid Validator invocation on FactoryBean.getObjectType/isSingleton
        if (isFactoryBeanMetadataMethod(invocation.getMethod())) {
            return invocation.proceed();
        }

        Class<?>[] groups = determineValidationGroups(invocation);

        // Standard Bean Validation 1.1 API
        ExecutableValidator execVal = this.validator.forExecutables();
        Method methodToValidate = invocation.getMethod();
        Object[] parameterValues = invocation.getArguments();
        Set<ConstraintViolation<Object>> result = new LinkedHashSet<>();

        //验证@validated注解的方法入参
        for (int i = 0; i < methodToValidate.getParameters().length; i++) {
            Parameter parameter = methodToValidate.getParameters()[i];
            Validated[] validated = parameter.getAnnotationsByType(Validated.class);
            if (validated != null && validated.length > 0) {
                Class<?>[] validatedGroupClasses = validated[0].value();
                if(parameterValues[i] != null){
                    Set<ConstraintViolation<Object>> set2 = validator.validate(parameterValues[i], groups);
                    result.addAll(set2);
                }
            }
        }

        //验证方法中的基本类型参数，和@valid注解的实体
        result.addAll(execVal.validateParameters(
                invocation.getThis(), methodToValidate, invocation.getArguments(), groups));

        if (!result.isEmpty()) {
            throw new ConstraintViolationException(result);
        }

        Object returnValue = invocation.proceed();

        result = execVal.validateReturnValue(invocation.getThis(), methodToValidate, returnValue, groups);
        if (!result.isEmpty()) {
            throw new ConstraintViolationException(result);
        }

        return returnValue;
    }

    private boolean isFactoryBeanMetadataMethod(Method method) {
        Class<?> clazz = method.getDeclaringClass();

        // Call from interface-based proxy handle, allowing for an efficient check?
        if (clazz.isInterface()) {
            return ((clazz == FactoryBean.class || clazz == SmartFactoryBean.class) &&
                    !method.getName().equals("getObject"));
        }

        // Call from CGLIB proxy handle, potentially implementing a FactoryBean method?
        Class<?> factoryBeanType = null;
        if (SmartFactoryBean.class.isAssignableFrom(clazz)) {
            factoryBeanType = SmartFactoryBean.class;
        }
        else if (FactoryBean.class.isAssignableFrom(clazz)) {
            factoryBeanType = FactoryBean.class;
        }
        return (factoryBeanType != null && !method.getName().equals("getObject") &&
                ClassUtils.hasMethod(factoryBeanType, method.getName(), method.getParameterTypes()));
    }
}

