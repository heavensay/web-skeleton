package com.www.skeleton.service.hello;

import com.www.skeleton.service.hello.bean.Pocket;

/**
 * @author lijianyu
 * @date 2019/1/31 15:22
 */
public interface HelloService {

    String getHappy();
    String getHappy(Pocket pocket, String condition);

}
