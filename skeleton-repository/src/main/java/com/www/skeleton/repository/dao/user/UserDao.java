package com.www.skeleton.repository.dao.user;

import com.www.skeleton.repository.po.user.User;
import com.www.skeleton.repository.po.user.UserDTO;
import com.www.skeleton.repository.po.user.UserExample;

import java.util.List;

/**
 * @author lijianyu
 * @date 2019/2/18 13:38
 */
public interface UserDao {

    User queryUserByName(String username);

    List<User> selectByExample(UserExample example);

    UserDTO queryUserAndPerms(String userName);
}
