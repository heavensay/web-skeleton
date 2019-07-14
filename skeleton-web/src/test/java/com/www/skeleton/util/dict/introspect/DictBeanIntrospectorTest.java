package com.www.skeleton.util.dict.introspect;

import com.alibaba.fastjson.JSON;
import com.www.skeleton.util.dict.SysDictManager;
import com.www.skeleton.util.model.ApiResponse;
import com.www.skeleton.web.controller.hello.HelloBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DictBeanIntrospectorTest {

    @Test
    public void acquireDictMetadata() {
        List<DictMetadata> list = DictBeanIntrospector.acquireDictMetadata(ArrayList.class);
        System.out.println(JSON.toJSONString(list));

        System.out.println(JSON.toJSONString(DictBeanIntrospector.acquireDictMetadata(HelloBean.class)));
    }

    @Test
    public void complexBeanTest() {
        System.out.println(JSON.toJSONString(DictBeanIntrospector.acquireDictMetadata(ApiResponse.class)));

        HelloBean helloBean = new HelloBean();
        helloBean.setName("ddddd");
        helloBean.setGentleman("lady");
        helloBean.setAnimalName("DOG");
        helloBean.setDigit(1);
        helloBean.setCountry("zh");

        ApiResponse response = new ApiResponse();
        response.setData(helloBean);

        SysDictManager.assignValueLabel(response);
        System.out.println(JSON.toJSONString(response));
    }
}