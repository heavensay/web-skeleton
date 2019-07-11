package com.www.skeleton.util.dict.source;

import java.util.Collection;

/**
 * @author ljy
 * @date 2019/7/11 20:57
 */
public interface DictSourceManage {

    void register(IDictSource dictSource);

    void unregister(IDictSource dictSource);
}
