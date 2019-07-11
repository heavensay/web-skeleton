package com.www.skeleton.util.dict.source;

import com.www.skeleton.util.dict.InnerDictKey;

/**
 * @author ljy
 * @date 2019/7/5 17:21
 */
public interface IDictSource {

    /**
     * 加载数据
     */
    void loadData();

    /**
     * 清空数据
     */
    void clearData();

    /**
     * 重新刷新数据
     * 常规做法先执行{@link #clearData()},再执行{@link #loadData()} }
     */
    void refreshData();

    /**
     * 是否包含数据
     * @param dictKey
     * @return
     */
    boolean containKey(InnerDictKey dictKey);

    /**
     *
     * @param dictKey
     * @return
     */
    Object get(InnerDictKey dictKey);

}
