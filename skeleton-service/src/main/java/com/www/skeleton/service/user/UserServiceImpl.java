package com.www.skeleton.service.user;

import com.www.skeleton.repository.dao.user.UserDao;
import com.www.skeleton.repository.po.user.User;
import com.www.skeleton.repository.po.user.UserMix;
import com.www.skeleton.service.common.exception.ServiceException;
import com.www.skeleton.util.HexUtil;
import com.www.skeleton.util.MessageDigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public void registerUser(String username, String password, String confirmPassword, String salt) {
        User existUser = userDao.queryUserByName(username);
        if(Objects.nonNull(existUser)){
            throw new ServiceException("user.register.username.exist_51001",username);
        }

        //hex存储sha256对应的密码
        String hexPassword = HexUtil.encodeHexStr(MessageDigestUtil.sha256(password+salt));

        User user = new User();
        user.setPassword(hexPassword);
        user.setUserName(username);
        user.setSalt(salt);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDao.insert(user);
    }
}
