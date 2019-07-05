package com.www.skeleton.util.dict.annotation;

import com.www.skeleton.util.dict.InnerDictKey;

import java.util.Map;

/**
 * @author ljy
 * @date 2019/7/5 17:21
 */
public interface IDictSource {

    Map<InnerDictKey,Object> loadData();

}
