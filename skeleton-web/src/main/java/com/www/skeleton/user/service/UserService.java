package com.www.skeleton.user.service;

import com.www.skeleton.user.repository.po.User;
import com.www.skeleton.user.repository.po.UserMix;

import java.util.List;

/**
 * @author lijianyu
 * @date 2019/2/18 12:01
 */
public interface UserService {

    List<User> listUser();

    User queryUserByName(String username);

    UserMix queryUserAndPerms(String userName);
}
