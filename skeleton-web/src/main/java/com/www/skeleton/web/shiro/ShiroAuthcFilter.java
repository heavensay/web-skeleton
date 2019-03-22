package com.www.skeleton.web.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * authc用户未认证过，不重定向到登录界面，直接返回错误信息
 * 前后端分离开发模式中，页面跳转由前端页面控制
 * @author lijianyu
 * @date 2019/3/22 13:41
 */
@Slf4j
public class ShiroAuthcFilter extends /*FormAuthenticationFilter*/AuthenticationFilter{
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        throw new AuthenticationException("用户需要登录才能操作");
    }
}
