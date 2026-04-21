package com.www.skeleton.repository.dao.user;

import com.www.skeleton.repository.mapper.user.UserMapper;
import com.www.skeleton.repository.po.user.User;
import com.www.skeleton.repository.po.user.UserExample;
import com.www.skeleton.repository.po.user.UserMix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lijianyu
 * @date 2019/2/18 13:40
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByName(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<User> selectByExample(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Override
    public UserMix queryUserAndPerms(String userName) {
        return userMapper.queryUserAndPerms(userName);
    }
}
