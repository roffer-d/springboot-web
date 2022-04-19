package com.roffer.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author roffer
 */
@SpringBootApplication
@ComponentScan("com.roffer")
@MapperScan("com.roffer.web.modules.*.mapper")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        System.out.println("server started. run:\n\thttp://localhost");
    }

}
