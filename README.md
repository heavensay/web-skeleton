web开发基本框架

1shiro权限管理控制，需要perms*表支持，采用mybatis数据持久化框架；采用token方案，不使用http session，保持服务器无状态化
1.1权限支持url粗粒度统一配置
1.2权限支持用户-角色-权限的细粒度配置
2message格式统一返回ApiResponse,框架已经自动封住；
3Controller异常统一处理，包括404,500等错误，错误信息使用配置文件，支持国际化i18n；业务异常统一使用ServiceException，
开发人员需要在message.properties配置对应提示信息
4待完成：yunloan-common缓存框架、工具包、数据库连接分离
5
6
    