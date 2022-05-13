#Springboot vue3 基础框架
目前已完成基本的系统功能，包含：  

* 用户管理（包含用户角色配置）  
* 角色管理（包含角色权限配置）  
* 菜单管理（包含菜单权限配置，级别到按钮）  
* 日志查询  
* 字典管理

运行前准备工作 
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
* 执行依赖包安装
    ```
    npm i
    ```
* 修改根目录下的.env.development中VUE_APP_BASE_URL值（该值就是后端接口请求的context path)
* 修改根目录下的vue.config.js文件
    ```
    修改后端接口请求：
    '/basic': {//对应.env.development中VUE_APP_BASE_URL值
        target: "http://localhost",//修改成自己的
        changeOrigin: true,
        pathRewrite: {
            "/basic": ""
        }
    },
    ```
* 启动项目
    ```
    npm run serve
    ```
* 项目打包发布
    ```
    修改.env.production中VUE_APP_BASE_URL值为后端生产context path
    
    执行：
    npm run build
  
    将打包后的目录上传到服务器，配置nginx代理请求
    ```
  
* 登录账号
    ```
    admin
    123456
    ```