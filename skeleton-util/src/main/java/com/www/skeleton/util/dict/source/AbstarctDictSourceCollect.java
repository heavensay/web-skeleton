package com.www.skeleton.util.dict.source;

import com.www.skeleton.util.dict.DictEnumSourceHelper;
import com.www.skeleton.util.dict.InnerDictKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ljy
 * @date 2019/7/9 21:35
 */
public abstract class AbstarctDictSourceCollect implements IDictSource {

    public AbstarctDictSourceCollect(){}

    private List<Class> list = new ArrayList();

    private Map<InnerDictKey, Object> dictDatas = new ConcurrentHashMap();

    /*数据源是否初始化标志*/
    protected boolean isInit = false;

    private Object lock = new Object();

    public void initData() {
        synchronized (lock) {
            if (!isInit()) {
                loadData();
                isInit = true;
            }
        }
    }

    @Override
    public abstract void loadData();

    @Override
    public void clearData() {
        dictDatas.clear();
        isInit = false;
    }

    @Override
    public void refreshData() {
        clearData();
        loadData();
        isInit = true;
    }

    @Override
    public boolean containKey(InnerDictKey dictKey) {
        if(!isInit()){
            initData();
        }
        return dictDatas.containsKey(dictKey);
    }

    @Override
    public Object get(InnerDictKey dictKey) {
        if(!isInit()){
            initData();
        }
        return dictDatas.get(dictKey);
    }

    public Map<InnerDictKey, Object> getDictData(){
        return dictDatas;
    }

    public boolean isInit(){
        if(dictDatas.size()>0 && !isInit){
            isInit = true;
        }
        return isInit;
    }
}
