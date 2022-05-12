package com.roffer.common.http;

/**
 * @author Dulongfei
 * @description http请求返回状态码定义
 * @date 2022/4/19 10:34
 */
public enum ConstEnum {
    //请求成功
    SUCCESS("请求成功", 200),
    //请求失败
    FAIL("请求失败", 500),
    NOT_LOGIN("登录失效", 10001),
    NO_USER("账号密码错误", 10002),
    NO_AUTH("无操作权限", 10003);

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private ConstEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
