package com.www.skeleton.util.dict.introspect;

import java.lang.reflect.Method;

public class ObjectDictMetadata implements DictMetadata {

    private String objectFieldName;

    private Method objectFieldReadMethod;

    public ObjectDictMetadata(String objectFieldName, Method objectFieldReadMethod) {
        this.objectFieldName = objectFieldName;
        this.objectFieldReadMethod = objectFieldReadMethod;
    }

    public String getObjectFieldName() {
        return objectFieldName;
    }

    public void setObjectFieldName(String objectFieldName) {
        this.objectFieldName = objectFieldName;
    }

    public Method getObjectFieldReadMethod() {
        return objectFieldReadMethod;
    }

    public void setObjectFieldReadMethod(Method objectFieldReadMethod) {
        this.objectFieldReadMethod = objectFieldReadMethod;
    }
}
