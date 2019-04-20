package com.www.skeleton.util;

import org.hibernate.validator.internal.engine.ConfigurationImpl;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.*;
import javax.validation.executable.ExecutableValidator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

public class ValidationUtil {

    private static ValidatorFactory vFactory;
    private static ExecutableValidator executableValidator;
    private static Validator validator;

    static {
        ConfigurationImpl configuration = ((ConfigurationImpl)Validation.byDefaultProvider().configure());
        configuration.allowOverridingMethodAlterParameterConstraint(true);
        configuration.allowMultipleCascadedValidationOnReturnValues(false);
        configuration.allowParallelMethodsDefineParameterConstraints(false);
        vFactory = configuration.buildValidatorFactory();
        executableValidator = vFactory.getValidator().forExecutables();
        validator = vFactory.getValidator();
    }
    public static <T> void validate(T t, Class<?>... groups) throws ValidationException {
        //下面方法指定message文件路径
//        Validator validator = Validation.byDefaultProvider()
//                .configure()
//                .messageInterpolator(new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("ValidationMessages.properties" )))
//                .buildValidatorFactory()
//                .getValidator();
        Set<ConstraintViolation<T>> set = validator.validate(t, groups);
        if (set.size() > 0) {
            StringBuilder validateError = new StringBuilder();
            appendError(set,validateError);
            throw new ConstraintDeclarationException(validateError.toString());
        }
    }

    public static <T> void validateProperty(T object,
                                            Method method,
                                            Object[] parameterValues,
                                            Class<?>... groups) throws ValidationException {
        Set<ConstraintViolation<T>> set = executableValidator.validateParameters(object, method, parameterValues, groups);
        if (set.size() > 0) {
            StringBuilder validateError = new StringBuilder();
            appendError(set,validateError);
            throw new ConstraintDeclarationException(validateError.toString());
        }
    }


    /**
     * 方法参数验证 兼容spring @validated注解验证
     * @param object
     * @param method
     * @param parameterValues
     * @param groups
     * @param <T>
     * @throws ValidationException
     */
    public static <T> void validatePropertyCompliable(T object,
                                                      Method method,
                                                      Object[] parameterValues,
                                                      Class<?>... groups) throws ValidationException {
        StringBuilder validateError = new StringBuilder();

        for (int i = 0; i < method.getParameters().length; i++) {
            Parameter parameter = method.getParameters()[i];
            Validated[] validated = parameter.getAnnotationsByType(Validated.class);
            if (validated != null && validated.length > 0) {
                Class<?>[] validatedGroupClasses = validated[0].value();
//                validate(parameterValues[i],validatedGroupClasses);

                Set<ConstraintViolation<Object>> set = vFactory.getValidator().validate(parameterValues[i], groups);
                appendError(set, validateError);
            }
        }


        Set<ConstraintViolation<T>> set = executableValidator.validateParameters(object, method, parameterValues, groups);
        appendError(set, validateError);
        if (validateError.length() > 0) {
            throw new ConstraintDeclarationException(validateError.toString());
        }
    }

    private static <T> void appendError(Set<ConstraintViolation<T>> set, StringBuilder errorBuilder) {
        for (ConstraintViolation val : set) {
            errorBuilder.append(val.getMessage() + ";");
        }
    }

    private Object[] determineValidationHints(Annotation ann) {
        Validated validatedAnn = AnnotationUtils.getAnnotation(ann, Validated.class);
        if (validatedAnn != null || ann.annotationType().getSimpleName().startsWith("Valid")) {
            Object hints = (validatedAnn != null ? validatedAnn.value() : AnnotationUtils.getValue(ann));
            if (hints == null) {
                return new Object[0];
            }
            return (hints instanceof Object[] ? (Object[]) hints : new Object[]{hints});
        }
        return null;
    }
}
