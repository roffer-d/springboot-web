package com.roffer.web.exception;

import com.roffer.common.http.ConstEnum;
import lombok.Data;

/**
 * @author Roffer
 * @description 自定义异常
 * @date 2022/5/12 09:22
 */
@Data
public class BaseException extends RuntimeException{
    private int code;
    private String message;

    public BaseException(){
        super();
    }

    public BaseException(ConstEnum constEnum){
        super();
        this.code = constEnum.getCode();
        this.message = constEnum.getMsg();
    }
}
