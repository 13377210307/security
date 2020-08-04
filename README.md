## Seurity学习
#### 模块一
**http-basic：简单密码模式**
* 引入依赖 主要引入security依赖
* 修改配置类 配置端口号
* 配置启动类
* 创建WebSecurityConfiguration配置 配置拦截所有请求
* 创建controller进行测试 
* 默认账号为user，密码自动生成

**http-basic：自定义账号密码**
* 修改yml配置文件

#### 模块二
**form-login：表单验证模式**

**需求**
* admin可以访问/admin,/user等接口
* user只可以访问/user接口
* index对外开放，无需认证

**WebSecurityConfiguration**

此次对于用户认证使用内存，资源认证也需要对用户角色进行控制

#### 模块三
**form-login-handle：自定义登录验证结果处理**

对于前后端分离项目，期望响应结果为JSON而不是html

#### 模块四
**session**
* always：如果当前请求没有session存在，springSecurity创建一个session。
* never：springSecurity将永远不会主动创建session，但session已经存在，它将使用该session
* ifRequire（默认）：springSecurity在需要时才创建session
* stateless：springSecurity不会创建或使用任何session。适用于接口型的无状态应用，该方式节省资源

**设置session过期时间**
* 在yml配置中可进行session过期配置
* WebSecurityConfiguration中配置session过期的跳转路径

**session多端登录问题**
* 设置session过期或非法策略类**
* webSecurityConfiguration中设置session最大只存在一个并设置session非法处理策略


