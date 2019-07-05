package com.www.skeleton.util.dict;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ljy
 * @date 2019/7/4 13:53
 */
public class SysDictManager implements IDictOperator {

    private static Map<InnerDictKey,Object> map;

    private static Class DEFAULT_CLASS = Object.class;

    static{
        map = new ConcurrentHashMap<>();
        add(DEFAULT_CLASS,"user","gender","lady","女士");
        add(DEFAULT_CLASS,"user","gender","gentleman","男士");
    }

    public static Object get(Class type,String code,String value){
        InnerDictKey dictKey = new InnerDictKey(type,type.getName(),code,value);
        return get(dictKey);
    }

    public static Object get(Class type,String category,String code,String value){
        InnerDictKey dictKey = new InnerDictKey(type,category,code,value);
        return get(dictKey);
    }

    public static void add(Class type,String code,String value,String valueLabel){
        InnerDictKey dictKey = new InnerDictKey(type,type.getName(),code,value);
        put(dictKey,valueLabel);
    }

    public static void add(Class type,String category,String code,String value,String valueLabel){
        InnerDictKey dictKey = new InnerDictKey(type,category,code,value);
        put(dictKey,valueLabel);
    }
}

