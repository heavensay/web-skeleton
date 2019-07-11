package com.www.skeleton.util.dict;

/**
 * @author ljy
 * @date 2019/7/5 17:21
 */
public interface IDictOperator {

    boolean containKey(InnerDictKey dictKey);

    Object get(InnerDictKey dictKey);
}
