package com.www.skeleton.util.dict.source;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ljy
 * @date 2019/7/11 20:59
 */
public class DefaultDictSourceManage implements DictSourceManage {

    private static Collection<IDictSource> dictSources = new ArrayList<>();

    @Override
    public void register(IDictSource dictSource) {
        dictSources.add(dictSource);
    }

    @Override
    public void unregister(IDictSource dictSource) {
        dictSources.remove(dictSource);
    }

    public Collection<IDictSource> getAllDictSource(){
        return dictSources;
    }
}
