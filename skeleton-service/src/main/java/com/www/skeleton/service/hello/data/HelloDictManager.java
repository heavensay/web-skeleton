package com.www.skeleton.service.hello.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ljy
 * @date 2019/7/3 17:55
 */
public class HelloDictManager {

    private static Map<String,String> map = new HashMap();

    static{
        map.put("lady","女士");
        map.put("gentleman","先生");
    }

    public String get(String key){
        return map.get(key);
    }
}
