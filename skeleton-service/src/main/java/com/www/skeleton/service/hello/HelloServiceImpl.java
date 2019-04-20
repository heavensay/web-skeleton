package com.www.skeleton.service.hello;

import com.www.skeleton.repository.dao.hello.HelloDao;
import com.www.skeleton.service.hello.bean.Pocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author lijianyu
 * @date 2019/1/31 15:22
 */
@Service("helloService")
@Validated
public class HelloServiceImpl implements HelloService {

    @Autowired
    private HelloDao helloDao;

    @Override
    public String getHappy() {
        return helloDao.getHappy();
    }

    @Override
    public String getHappy(@Validated Pocket pocket, @NotNull(message = "快乐不能为空") String condition) {
        return condition;
    }

}
