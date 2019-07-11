package com.www.skeleton.service.hello;

import com.www.skeleton.repository.mapper.sys.SysDictMapper;
import com.www.skeleton.repository.po.sys.SysDict;
import com.www.skeleton.util.dict.DictKey;
import com.www.skeleton.util.dict.source.AbstarctDictSourceCollect;
import com.www.skeleton.util.spring.SpringContextHolder;

import java.util.List;
import java.util.Map;

/**
 * @author ljy
 * @date 2019/7/10 21:13
 */
public class SystemDictDataSourceCollect extends AbstarctDictSourceCollect {

    private SysDictMapper sysDictMapper = SpringContextHolder.getBean(SysDictMapper.class);

    @Override
    public void loadData() {
        Map<DictKey, Object> dictDatas = getDictData();
        List<SysDict> sysDicts = sysDictMapper.listAll();
        sysDicts.stream().forEach(dict->{
            String category = dict.getCategory();
            String code = dict.getCode();
            String value = dict.getValue();
            String valueLabel = dict.getValueLabel();

            DictKey key = new DictKey(category,code,value);
            dictDatas.put(key,valueLabel);
        });
    }
}
