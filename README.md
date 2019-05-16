#web开发基本框架

1. shiro权限管理控制

需要perms\*表支持，采用mybatis数据持久化框架；采用token方案，不使用http session，保持服务器无状态化
需要把doc/db/perms.sql导入你的数据库中，另外数据源需要配置正确，对应application.properties文件中参数spring.datasource.\*;

支持功能：

* 账号注册密码支持salt加密
* 权限支持url粗粒度统一配置
* 权限支持用户-角色-权限的细粒度配置

接口访问URL：

* 登录：http://127.0.0.1:8080/user/login?username=test1&password=123456
* 注册：http://127.0.0.1:8080/user/registerUser?username=test1&password=123456&confirmPassword=123456&salt=abcdefghigk
* 权限接口访问：http://127.0.0.1:8080/hello/getHappy

2. message格式统一返回ApiResponse,框架已经自动封住；
3. Controller异常统一处理，包括404,500等错误，错误信息使用配置文件，支持国际化i18n；业务异常统一使用ServiceException，
开发人员需要在message.properties配置对应提示信息
4. 待完成：yunloan-common缓存框架、工具包、数据库连接分离


