package com.roffer.web.enums;

/**
 * @author Dulongfei
 * @description redis key常量定义
 * @date 2022/4/19 10:34
 */
public enum RedisConstEnum {
    /** ttl单位秒 **/
    MENU("用户菜单", "menu_list_",60 * 60 * 24),
    ROLE("用户角色", "role_list_",60 * 60 * 24),
    LOGIN_IMAGE_INFO("登录拖拽图片信息", "image_info_",60 * 5);

    private String key;
    private String value;
    private long ttl;

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

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    private RedisConstEnum(String msg, String value,long ttl) {
        this.key = msg;
        this.value = value;
        this.ttl = ttl;
    }
}
