package com.roffer.web.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dulongfei
 * @description 拦截器配置
 * @date 2022/4/19 09:10
 */
@Component
public class InterceptorConfig implements WebMvcConfigurer {

    private AuthInterceptor authInterceptor;

    /*
     * @param tokenInterceptor
     * @return
     * @introduction: 构造方法
     * @date 2021/5/19 21:41
     */

    public InterceptorConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    /*
     * @param registry
     * @return void
     * @introduction: 设置拦截路径
     * @date 2021/5/19 21:41
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //放开权限
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/login");
        excludePath.add("/basicMenu/getAuth");
        excludePath.add("/logOut");
        excludePath.add("/createImg");
        excludePath.add("/checkImg");
        excludePath.add("/swagger-resources/**");
        excludePath.add("/swagger-ui.html/**");

        //放开所有
//        excludePath.add("/**");

        //静态资源
        excludePath.add("/static/**");

        //注入拦截器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);

    }

    /*
     * @param registry
     * @return void
     * @introduction: 跨域支持
     * @date 2021/5/19 21:44
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
                .maxAge(3600 * 24);
    }

}
