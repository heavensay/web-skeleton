package com.www.skeleton.util.dict;

import com.www.skeleton.util.dict.introspect.DictBeanIntrospector;
import com.www.skeleton.util.dict.introspect.DictMetadata;
import com.www.skeleton.util.dict.introspect.ObjectDictMetadata;
import com.www.skeleton.util.dict.introspect.PrimitiveDictMetadata;
import com.www.skeleton.util.dict.source.DefaultDictSourceManage;
import com.www.skeleton.util.dict.source.EnumDictSourceCollect;
import com.www.skeleton.util.dict.source.IDictSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

/**
 * 字典数据的便捷操作工具类
 * @author ljy
 * @date 2019/7/4 13:53
 */
public final class SysDictManager{

    private static Logger logger = LoggerFactory.getLogger(SysDictManager.class);

    private static DefaultDictSourceManage dictSourceManage = new DefaultDictSourceManage();

    static{
        dictSourceManage.register(EnumDictSourceCollect.INSTANCE);
    }

    public static void registerDictSource(IDictSource dictSource){
        dictSourceManage.register(dictSource);
    }

    public static Object get(Class type, String code, Object value) {
        DictKey dictKey = new DictKey(type, type.getName(), code, value);
        return get(dictKey);
    }

    public static Object get(Class type, String category, String code, Object value) {
        DictKey dictKey = new DictKey(type, category, code, value);
        return get(dictKey);
    }

    public static void assignValueLabel(Object instance){
        if(instance == null) return;
        if(instance instanceof Collection){
            Object[] beans = ((Collection) instance).toArray();
            for (Object b : beans) {
                assignValueLabel(b);
            }
        }else{
            List<DictMetadata> dictMetadata = DictBeanIntrospector.acquireDictMetadata(instance.getClass());

            dictMetadata.stream().forEach(dm -> {
                try {
                    if (dm instanceof PrimitiveDictMetadata) {
                        PrimitiveDictMetadata primitiveDictMetadata = (PrimitiveDictMetadata) dm;

                        Object value = primitiveDictMetadata.getValueColumnReadMethod().invoke(instance, null);
                        Object valueLabel = get(primitiveDictMetadata.getType(), primitiveDictMetadata.getCategory(), primitiveDictMetadata.getCode(), value);
                        primitiveDictMetadata.getValueLabelWriteMethod().invoke(instance, valueLabel);

                    } else if (dm instanceof ObjectDictMetadata) {
                        ObjectDictMetadata objectDictMetadata = (ObjectDictMetadata) dm;
                        Object bean = objectDictMetadata.getObjectFieldReadMethod().invoke(instance, null);
                        assignValueLabel(bean);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("字典翻译失败", e);
                }
            });
        }
    }

    public boolean containKey(DictKey dictKey) {
        for (IDictSource dictSource:dictSourceManage.getAllDictSource()) {
            boolean isExist = dictSource.containKey(dictKey);
            if(isExist){
                return isExist;
            }
        }
        return false;
    }

    public static Object get(DictKey dictKey) {
        for (IDictSource dictSource:dictSourceManage.getAllDictSource()) {
            Object value = dictSource.get(dictKey);
            if(value!=null){
                return value;
            }
        }
        return null;
    }

}

