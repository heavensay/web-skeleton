package com.www.skeleton.web.shiro;

import com.www.skeleton.repository.po.user.User;
import com.www.skeleton.util.HexUtil;
import com.www.skeleton.util.MessageDigestUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/*
 * 密码校验方法继承SimpleCredentialsMatcher或HashedCredentialsMatcher类，自定义实现doCredentialsMatch方法
 * */
public class CredentialMatcher extends SimpleCredentialsMatcher {
    
	/*
	 * 这里是进行密码匹配的方法，自己定义
	 * 通过用户的唯一标识得到 AuthenticationInfo 然后和 AuthenticationToken （用户名 密码），进行比较
	 * */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = new String(usernamePasswordToken.getPassword());
        User credential = (User) info.getCredentials();//数据库里的密码
        String hexDigest = HexUtil.encodeHexStr(MessageDigestUtil.sha256(password+credential.getSalt()));

        return this.equals(credential.getPassword(), hexDigest);
    }
}
