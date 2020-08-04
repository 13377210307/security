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


