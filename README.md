#Springboot vue3 基础框架

##目前已完成基本的系统功能，包含：  

* 用户管理（包含用户角色配置）  
* 角色管理（包含角色权限配置）  
* 菜单管理（包含菜单权限配置，级别到按钮）  
* 日志查询  
* 字典管理

运行前准备工作 
* mysql版本号：8
* 新建mysql数据库**basic**   
* 将web模块resources目录下的basic.sql导入到mysql
* 修改application.yml中**jdbc**连接和**redis**连接（登录和权限认证使用redis存储）
* 修改mybatiesplus-config.yml中的配置（配置中已标注说明）

代码生成器
* 修改web模块下**mybatiesplus-config.yml**  
* 可配置是否生成vue前端页面的增删改查
* 运行web模块com.roffer.web下的**Generator**

前端项目
* git地址：
    ```
    https://github.com/roffer-d/vue3-web
    ```