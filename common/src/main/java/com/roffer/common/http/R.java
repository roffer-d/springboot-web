package com.roffer.common.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dulongfei
 * @description 统一返回结果类
 * @date 2022/4/8 16:45
 */
@Data
public class R {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private R() {
    }

    //成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ConstEnum.SUCCESS.getCode());
        r.setMessage(ConstEnum.SUCCESS.getMsg());
        return r;
    }

    //失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ConstEnum.FAIL.getCode());
        r.setMessage(ConstEnum.FAIL.getMsg());
        return r;
    }

    //自定义错误
    public static R customError(Integer code, String msg) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
