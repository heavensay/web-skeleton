web开发基本框架

1shiro权限管理控制，需要perms*表支持，采用mybatis数据持久化框架；采用token方案，不使用http session，保持服务器无状态化
需要把doc/db/perms.sql导入你的数据库中，另外数据源需要配置正确，对应application.properties文件中参数spring.datasource.*
1.1权限支持url粗粒度统一配置
1.2权限支持用户-角色-权限的细粒度配置
2message格式统一返回ApiResponse,框架已经自动封住；
3Controller异常统一处理，包括404,500等错误，错误信息使用配置文件，支持国际化i18n；业务异常统一使用ServiceException，
开发人员需要在message.properties配置对应提示信息
4待完成：yunloan-common缓存框架、工具包、数据库连接分离
5支持application/json body参数解析，使用@JsonArg注解即可
6支持controller返回实体中字典自动解析；使用@DictKey
    
测试url：    
普通请求：http://127.0.0.1:8081/hello/getHappy
登录：http://127.0.0.1:8081/user/login?username=test&password=123456&rememberMe=true
需要登录才能访问：http://127.0.0.1:8081/hello/needPerms 
需要rememberme才能访问：http://127.0.0.1:8081/hello/needRememberme
i18n:http://127.0.0.1:8081/hello/i18n，浏览器需要设置accept-language