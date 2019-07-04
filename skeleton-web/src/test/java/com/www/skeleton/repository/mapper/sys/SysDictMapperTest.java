package com.www.skeleton.repository.mapper.sys;

import com.www.skeleton.BaseConfigTest;
import com.www.skeleton.repository.po.sys.SysDict;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

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