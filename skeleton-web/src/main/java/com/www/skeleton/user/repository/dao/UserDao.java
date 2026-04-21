package com.www.skeleton.user.repository.dao;

import com.www.skeleton.user.repository.po.User;
import com.www.skeleton.user.repository.po.UserExample;
import com.www.skeleton.user.repository.po.UserMix;

import java.util.List;

/**
 * @author lijianyu
 * @date 2019/2/18 13:38
 */
public interface UserDao {

    User queryUserByName(String username);

    List<User> selectByExample(UserExample example);

    UserMix queryUserAndPerms(String userName);
}
