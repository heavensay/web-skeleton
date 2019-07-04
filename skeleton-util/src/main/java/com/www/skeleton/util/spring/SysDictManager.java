package com.www.skeleton.util.spring;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ljy
 * @date 2019/7/4 13:53
 */
public class SysDictManager {

    private String category;

    private String code;

    private String value;

    private String valueLabel;

    private static Map<String,String> map;

    static{
        map = new HashMap();
        add("default","gender","lady","女士");
        add("default","gender","gentleman","男士");
    }

    public static String get(String category,String code,String value){
        return map.get(category+code+value);
    }

    public static void add(String category,String code,String value,String valueLabel){
        map.put(category+code+value,valueLabel);
    }
}

