package com.www.skeleton.service.hello;

import com.www.skeleton.repository.dao.hello.HelloDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lijianyu
 * @date 2019/1/31 15:22
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @Autowired
    private HelloDao helloDao;

    @Override
    public String getHappy() {
        return helloDao.getHappy();
    }

}
