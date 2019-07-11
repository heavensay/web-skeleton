package com.www.skeleton.util.dict.source;

import com.www.skeleton.util.dict.DictEnumSourceHelper;
import com.www.skeleton.util.dict.DictKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 便捷收集Enum字典数据
 * @author ljy
 * @date 2019/7/9 21:35
 */
public class EnumDictSourceCollect extends AbstarctDictSourceCollect {

    public final static EnumDictSourceCollect INSTANCE = new EnumDictSourceCollect();

    private EnumDictSourceCollect(){}

    private List<Class> list = new ArrayList();

    @Override
    public void loadData() {
        list.stream().forEach(enumClass->{
            loadEnumData(enumClass);
        });
    }

    /**
     * 存储枚举字典数据
     * @param enumClass
     */
    public void loadEnumData(Class enumClass){
        if(!list.contains(enumClass)){
            list.add(enumClass);
        }
        Map<DictKey,Object> map = DictEnumSourceHelper.loadEnumSource(enumClass);
        getDictData().putAll(map);
    }
}
