package com.www.skeleton.util.dict;

import com.www.skeleton.util.dict.annotation.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对bean进行内省，获取需要进行字典转换的元数据
 * @author ljy
 * @date 2019/7/11 20:21
 */
public class DictBeanIntrospect {

    private static Logger logger = LoggerFactory.getLogger(DictBeanIntrospect.class);

    private static Map<Class,List<DictMetadata>> cache = new HashMap<>();
    /**
     * todo 要支持list<Object>，嵌套实体的自动转换
     * @param beanType
     * @return 不为null
     */
    public static List<DictMetadata> acquireDictMetadata(Class beanType){
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
        List<DictMetadata> list = new ArrayList();

        for (Field field : fields) {
            Dict dictKey = field.getAnnotation(Dict.class);
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

                DictMetadata dictMetadata = new DictMetadata(fieldName,type,category,code,valueColumn,valueColumnReadMethod,valueLabelWriteMethod);
                dictMetadata.setValueColumnReadMethod(valueColumnReadMethod);
                dictMetadata.setValueLabelWriteMethod(valueLabelWriteMethod);

                list.add(dictMetadata);
            }
        }

        return list;
    }

    public static class DictMetadata {
        private String fieldName;
        private Class type;
        private String category;
        private String code;
        private String valueColumn;
        private Method valueColumnReadMethod;
        private Method valueLabelWriteMethod;

        public DictMetadata(String fieldName, Class type, String category, String code, String valueColumn, Method valueColumnReadMethod, Method valueLabelWriteMethod) {
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
