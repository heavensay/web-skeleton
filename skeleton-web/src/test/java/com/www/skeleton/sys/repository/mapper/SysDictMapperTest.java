package com.www.skeleton.sys.repository.mapper;

import com.www.skeleton.BaseConfigTest;
import com.www.skeleton.sys.repository.po.SysDict;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ljy
 * @date 2019/7/4 11:58
 */
public class SysDictMapperTest extends BaseConfigTest{

    @Autowired
    private SysDictMapper sysDictMapper;

    @Test
    public void list() {
        List<SysDict> list = sysDictMapper.list("default","gender");
        Assert.assertTrue(list.size()>0);
    }
}