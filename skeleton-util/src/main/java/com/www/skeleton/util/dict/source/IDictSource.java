package com.www.skeleton.util.dict.source;

import com.www.skeleton.util.dict.DictKey;

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
    boolean containKey(DictKey dictKey);

    /**
     *
     * @param dictKey
     * @return
     */
    Object get(DictKey dictKey);

}
