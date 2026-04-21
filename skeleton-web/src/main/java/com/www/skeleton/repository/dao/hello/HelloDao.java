package com.www.skeleton.repository.dao.hello;

import org.springframework.stereotype.Repository;

/**
 * @author lijianyu
 * @date 2019/1/31 15:38
 */
@Repository("helloDao")
public class HelloDao {
    public String getHappy(){
        return "many money";
    }
}
