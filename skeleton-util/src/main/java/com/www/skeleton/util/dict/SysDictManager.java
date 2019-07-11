package com.www.skeleton.util.dict;

import com.www.skeleton.util.dict.source.EnumDictSourceCollect;
import com.www.skeleton.util.dict.source.IDictSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典数据的便捷操作工具类
 * @author ljy
 * @date 2019/7/4 13:53
 */
public final class SysDictManager{

    private static List<IDictSource> dictSources = new ArrayList<>();

    static{
        registerDictSource(EnumDictSourceCollect.INSTANCE);
    }

    public static void registerDictSource(IDictSource dictSource){
        dictSources.add(dictSource);
    }

    public static Object get(Class type, String code, String value) {
        InnerDictKey dictKey = new InnerDictKey(type, type.getName(), code, value);
        return get(dictKey);
    }

    public static Object get(Class type, String category, String code, String value) {
        InnerDictKey dictKey = new InnerDictKey(type, category, code, value);
        return get(dictKey);
    }

    public boolean containKey(InnerDictKey dictKey) {
        for (IDictSource dictSource:dictSources) {
            boolean isExist = dictSource.containKey(dictKey);
            if(isExist){
                return isExist;
            }
        }
        return false;
    }

    public static Object get(InnerDictKey dictKey) {
        for (IDictSource dictSource:dictSources) {
            Object value = dictSource.get(dictKey);
            if(value!=null){
                return value;
            }
        }
        return null;
    }

}

