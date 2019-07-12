package com.www.skeleton.util.dict;

import com.www.skeleton.util.dict.annotation.DictConfiguration;
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

    public static Map<DictKey,Object> resolveEnumSource(Class<? extends Enum> enumSource){
        DictEnumSource dictEnumSource = (DictEnumSource) enumSource.getDeclaredAnnotation(DictEnumSource.class);
        if (dictEnumSource == null) {
            return Collections.EMPTY_MAP;
        }

        Class type = enumSource;
        String category = DictConfiguration.DEFAULT_CATEGORY;
        String code = DictConfiguration.DEFAULT_CODE;

        String valueFieldName = dictEnumSource.valueFieldName();
        String valueLabelFieldName = dictEnumSource.valueLabelFieldName();

        Method valuesMethod = null;
        try {
            valuesMethod = enumSource.getMethod("values", null);
            Enum[] enums = (Enum[]) valuesMethod.invoke(null, null);

            Field valueLabelField = enumSource.getDeclaredField(valueLabelFieldName);

            Map<DictKey,Object> map = new ConcurrentHashMap();
            for (Enum item : enums) {
                valueLabelField.setAccessible(true);
                Object valueLabel = valueLabelField.get(item);
                Object value = item.name();
                if(!isEmpty(valueFieldName)){
                    Field valueField = enumSource.getDeclaredField(valueFieldName);
                    valueField.setAccessible(true);
                    value = valueField.get(item);
                }
                DictKey dictKey = new DictKey(type,category,code,value);
                map.put(dictKey,valueLabel);
            }
            return map;
        }catch (RuntimeException e) {
            throw e;
        }catch (Exception e){
            throw new RuntimeException(enumSource.getName()+"数据加载失败",e);
        }
    }

    private static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }
}
