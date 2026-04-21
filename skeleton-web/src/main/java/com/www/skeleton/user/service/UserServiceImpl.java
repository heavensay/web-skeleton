package com.www.skeleton.user.service;

import com.www.skeleton.user.repository.dao.UserDao;
import com.www.skeleton.user.repository.po.User;
import com.www.skeleton.user.repository.po.UserMix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lijianyu
 * @date 2019/2/18 12:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> listUser() {
        return userDao.selectByExample(null);
    }

    @Override
    public User queryUserByName(String username) {
        return userDao.queryUserByName(username);
    }

    @Override
    public UserMix queryUserAndPerms(String userName) {
        return userDao.queryUserAndPerms(userName);
    }
}
