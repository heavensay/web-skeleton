package com.www.skeleton.web.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * @author lijianyu
 * @date 2019/2/18 11:13
 */

@Configuration
@Slf4j
public class ShiroConfiguration {
    //@Qualifier代表spring里面的

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);

        bean.setLoginUrl("/user/login");//提供登录到url
//        bean.setSuccessUrl("/index");//提供登陆成功的url
//        bean.setUnauthorizedUrl("/unauthorized");

        /*
         * 可以看DefaultFilter,这是一个枚举类，定义了很多的拦截器authc,anon等分别有对应的拦截器
         * */
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/login", "anon");
//        filterChainDefinitionMap.put("/loginUser", "anon");
//        filterChainDefinitionMap.put("/admin", "roles[admin]");//admin的url，要用角色是admin的才可以登录,对应的拦截器是RolesAuthorizationFilter
//        filterChainDefinitionMap.put("/edit", "perms[edit]");//拥有edit权限的用户才有资格去访问
//        filterChainDefinitionMap.put("/druid/**", "anon");//所有的druid请求，不需要拦截，anon对应的拦截器不会进行拦截
//        filterChainDefinitionMap.put("/**", "user");//所有的路径都拦截，被UserFilter拦截，这里会判断用户有没有登陆
        filterChainDefinitionMap.put("/**", "anon");//所有的路径都拦截，被UserFilter拦截，这里会判断用户有没有登陆
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);//设置一个拦截器链

        return bean;
    }


    /*
     * 注入一个securityManager
     * 原本以前我们是可以通过ini配置文件完成的，代码如下：
     *  1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
     * */
//    @Bean("securityManager")
//    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
//        //这个DefaultWebSecurityManager构造函数,会对Subject，realm等进行基本的参数注入
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//        manager.setRealm(authRealm);//往SecurityManager中注入Realm，代替原本的默认配置
//        return manager;
//    }

    @Bean
    public SecurityManager securityManager(SessionManager sessionManager,AuthRealm authRealm){
        SessionsSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(authRealm);//往SecurityManager中注入Realm，代替原本的默认配置
        return securityManager;
    }

    //自定义的Realm
    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        //这边可以选择是否将认证的缓存到内存中，现在有了这句代码就将认证信息缓存的内存中了
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        //最简单的情况就是明文直接匹配，然后就是加密匹配，这里的匹配工作则就是交给CredentialsMatcher来完成
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    /*
     * Realm在验证用户身份的时候，要进行密码匹配
     * 最简单的情况就是明文直接匹配，然后就是加密匹配，这里的匹配工作则就是交给CredentialsMatcher来完成
     * 支持任意数量的方案，包括纯文本比较、散列比较和其他方法。除非该方法重写，否则默认值为
     * */
    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }


    /*
     * 以下AuthorizationAttributeSourceAdvisor,DefaultAdvisorAutoProxyCreator两个类是为了支持shiro注解
     * */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public SessionIdGenerator sessionIdGenerator(){
        return new SessionIdGenerator() {
            @Override
            public Serializable generateId(Session session) {
                return UUID.randomUUID()+"kkk222";
            }
        };
    }

    @Bean
    public SessionDAO sessionDAO(){
        AbstractSessionDAO sessionDAO = new MemorySessionDAO();
        sessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return sessionDAO;
    }


    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setSessionIdCookie(simpleCookie());
        return sessionManager;
    }

    @Bean
    public SimpleCookie simpleCookie(){
        String TOKEN_NAME = "X_TOKEN2";
        SimpleCookie cookie = new SimpleCookie(TOKEN_NAME);
        return cookie;
    }
}
