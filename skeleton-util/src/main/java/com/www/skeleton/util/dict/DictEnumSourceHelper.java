package com.www.skeleton.util.dict;

import com.www.skeleton.util.dict.annotation.DictEnumSource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 便捷收集Enum字典数据
 * @author ljy
 * @date 2019/7/9 18:51
 */
public class DictEnumSourceHelper {

    public static Map<DictKey,Object> loadEnumSource(Class source){
        DictEnumSource dictEnumSource = (DictEnumSource) source.getDeclaredAnnotation(DictEnumSource.class);
        if (dictEnumSource == null || !Enum.class.isAssignableFrom(source)) {
            return Collections.EMPTY_MAP;
        }

        String category = isEmpty(dictEnumSource.category())?source.getName():dictEnumSource.category();
        String code = category;
        String valueFieldName = dictEnumSource.valueFieldName();
        String valueLabelFieldName = dictEnumSource.valueLabelFieldName();
        Class type = source;

        Method valuesMethod = null;
        try {
            valuesMethod = source.getMethod("values", null);
            Enum[] enums = (Enum[]) valuesMethod.invoke(null, null);

            Field valueLabelField = source.getDeclaredField(valueLabelFieldName);

            Map<DictKey,Object> map = new ConcurrentHashMap();
            for (Enum item : enums) {
                valueLabelField.setAccessible(true);
                Object valueLabel = valueLabelField.get(item);
                Object value = item.name();
                if(!isEmpty(valueFieldName)){
                    Field valueField = source.getDeclaredField(valueFieldName);
                    valueField.setAccessible(true);
                    value = valueField.get(item);
                }
                DictKey innerDictKey = new DictKey(type,category,code,value);
                map.put(innerDictKey,valueLabel);
            }
            return map;
        }catch (RuntimeException e) {
            throw e;
        }catch (Exception e){
            throw new RuntimeException(source.getName()+"数据加载失败",e);
        }
    }

    private static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }
}
