package com.roffer.web.annotation;

import java.lang.annotation.*;

/**
 * @author Roffer
 * @description 操作日志注解
 * @date 2022/5/12 10:09
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface LogRecords {
    String remark();

    // 操作类型
    OperLogEnum action();

    /**
     * 操作日志类型
     */
    public enum OperLogEnum {
        ADD,
        EDIT,
        DELETE,
        QUERY,
        UPLOAD,
        DOWNLOAD,
        EXPORT,
        IMPORT
    }
}
