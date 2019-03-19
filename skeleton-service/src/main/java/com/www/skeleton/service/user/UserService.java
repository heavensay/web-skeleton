package com.www.skeleton.service.user;

import com.www.skeleton.repository.po.user.User;
import com.www.skeleton.repository.po.user.UserMix;

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
