package com.www.skeleton.util.spring.bodyadvice;

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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 针对Controller返回值，进行字典自动转换
 * @author lijianyu
 * @date 2019/1/28 21:34
 */
@RestControllerAdvice
//@DependsOn("globalResponseHandler")
public class DictResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    Logger logger = LoggerFactory.getLogger(DictResponseBodyAdvice.class);

    private static Map<Class,List<DictKeyMetadata>> cache = new HashMap<>();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return acquireDictMetadata(returnType.getParameterType()).size()>0;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        List<DictKeyMetadata> list = acquireDictMetadata(returnType.getParameterType());
        for (DictKeyMetadata dm : list) {
            Object value = null;
            try {
                value = dm.getValueColumnReadMethod().invoke(body,null);
                Object valueLabel = SysDictManager.get(dm.getType(),dm.getCategory(),dm.getCode(),value.toString());
                dm.getValueLabelWriteMethod().invoke(body,valueLabel);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return body;
    }

    /**
     * todo 要支持list<Object>，嵌套实体的自动转换
     * @param beanType
     * @return 不为null
     */
    List<DictKeyMetadata> acquireDictMetadata(Class beanType){
        if(cache.get(beanType) != null){
            return cache.get(beanType);
        }
        //是实体类
        Field[] fields = beanType.getDeclaredFields();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(beanType);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
        List<DictKeyMetadata> list = new ArrayList();

        for (Field field : fields) {
            DictKey dictKey = field.getAnnotation(DictKey.class);
            if(dictKey != null){
                String fieldName = field.getName();
                Class type = dictKey.type();
                String category = dictKey.category();
                String code = dictKey.code();
                String valueColumn = dictKey.valueColumn();

                Method valueLabelWriteMethod = null;
                Method valueColumnReadMethod = null;

                for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                    if(fieldName.equals(propertyDescriptor.getName())){
                        valueLabelWriteMethod = propertyDescriptor.getWriteMethod();
                        break;
                    }
                }
                if(valueLabelWriteMethod == null){
                    logger.info("属性:"+fieldName+"对应的写入方法不存在");
                    break;
                }

                for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                    if(valueColumn.equals(propertyDescriptor.getName())){
                        valueColumnReadMethod = propertyDescriptor.getReadMethod();
                        break;
                    }
                }
                if(valueColumnReadMethod == null){
                    logger.info("属性:"+fieldName+"对应的读方法不存在");
                    break;
                }

                DictKeyMetadata dictMetadata = new DictKeyMetadata(fieldName,type,category,code,valueColumn,valueColumnReadMethod,valueLabelWriteMethod);
                dictMetadata.setValueColumnReadMethod(valueColumnReadMethod);
                dictMetadata.setValueLabelWriteMethod(valueLabelWriteMethod);

                list.add(dictMetadata);
            }
        }

        return list;
    }

    private Method getFieldMethod(String fieldName,BeanInfo beanInfo){
        return null;
    }

    class DictKeyMetadata {
        private String fieldName;
        private Class type;
        private String category;
        private String code;
        private String valueColumn;
        private Method valueColumnReadMethod;
        private Method valueLabelWriteMethod;

        public DictKeyMetadata(String fieldName,Class type,String category, String code, String valueColumn, Method valueColumnReadMethod, Method valueLabelWriteMethod) {
            this.fieldName = fieldName;
            this.type = type;
            this.category = category;
            this.code = code;
            this.valueColumn = valueColumn;
            this.valueColumnReadMethod = valueColumnReadMethod;
            this.valueLabelWriteMethod = valueLabelWriteMethod;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public Class getType() {
            return type;
        }

        public void setType(Class type) {
            this.type = type;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValueColumn() {
            return valueColumn;
        }

        public void setValueColumn(String valueColumn) {
            this.valueColumn = valueColumn;
        }

        public Method getValueColumnReadMethod() {
            return valueColumnReadMethod;
        }

        public void setValueColumnReadMethod(Method valueColumnReadMethod) {
            this.valueColumnReadMethod = valueColumnReadMethod;
        }

        public Method getValueLabelWriteMethod() {
            return valueLabelWriteMethod;
        }

        public void setValueLabelWriteMethod(Method valueLabelWriteMethod) {
            this.valueLabelWriteMethod = valueLabelWriteMethod;
        }
    }
}