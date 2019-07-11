package com.www.skeleton.util.dict;

import com.www.skeleton.util.dict.source.DefaultDictSourceManage;
import com.www.skeleton.util.dict.source.EnumDictSourceCollect;
import com.www.skeleton.util.dict.source.IDictSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

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

    public static void assignValueLabel(DictBeanIntrospect.DictMetadata dm,Object instance){
        try {
            Object value = dm.getValueColumnReadMethod().invoke(instance,null);
            Object valueLabel = get(dm.getType(),dm.getCategory(),dm.getCode(),value.toString());
            dm.getValueLabelWriteMethod().invoke(instance,valueLabel);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("字典翻译失败",e);
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

