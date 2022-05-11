package com.roffer.web.utils;

/**
 * @author Dulongfei
 * @description redis key常量定义
 * @date 2022/4/19 10:34
 */
public enum RedisConstEnum {
    MENU("用户菜单", "menu_list_"),
    ROLE("用户角色", "role_list_"),
    LOGIN_IMAGE_INFO("登录拖拽图片信息", "image_info_");

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private RedisConstEnum(String msg, String value) {
        this.key = msg;
        this.value = value;
    }
}
