package com.roffer.web.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

@Component
/**
 * @description mybatis-plus insert、update 自动插入时间策略配置
 * @author Dulongfei
 * @date 2022/4/12 15:11
 */
public class DbDateHandler implements MetaObjectHandler {
    private static Properties properties = new Properties();

    static {
        ClassPathResource classpathResource = new ClassPathResource("mybatiesplus-config.yml");
        try {
            InputStream fileInputStream = classpathResource.getInputStream();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(properties.getProperty("insertJavaFill"), new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(properties.getProperty("updateJavaFill"), new Date(), metaObject);
    }
}
