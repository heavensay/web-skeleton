package com.www.skeleton.util.dict.introspect;

import java.lang.reflect.Method;

public class PrimitiveDictMetadata implements DictMetadata {
    private String fieldName;
        private Class type;
        private String category;
        private String code;
        private String valueColumn;
        private Method valueColumnReadMethod;
        private Method valueLabelWriteMethod;

        public PrimitiveDictMetadata(String fieldName, Class type, String category, String code, String valueColumn, Method valueColumnReadMethod, Method valueLabelWriteMethod) {
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