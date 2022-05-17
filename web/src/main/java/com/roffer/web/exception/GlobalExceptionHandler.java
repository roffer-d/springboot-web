package com.roffer.web.exception;

import com.roffer.common.http.ConstEnum;
import com.roffer.common.http.R;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Roffer
 * @description 全局异常处理
 * @date 2022/5/12 09:30
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

    /**
      * @description 处理自定义异常
      * @param e
      * @author Roffer
      * @date 2022/5/12 09:42
      */
    @ExceptionHandler(value = BaseException.class)
    public R baseExceptionHandler(BaseException e) {
        logger.error("发生业务异常，异常信息：" + e.getMessage());
        e.printStackTrace();
        return R.customError(e.getCode(),e.getMessage());
    }

    /**
      * @description 处理空指针异常
      * @param e
      * @author Roffer
      * @date 2022/5/12 09:42
      */
    @ExceptionHandler(value = NullPointerException.class)
    public R exceptionHandler(NullPointerException e) {
        logger.error("发生空指针异常，异常信息：" + e.getMessage());
        e.printStackTrace();
        return R.customError(ConstEnum.FAIL.getCode(),ConstEnum.FAIL.getMsg());
    }

    /**
     * @description 处理其它异常
     * @param e
     * @author Roffer
     * @date 2022/5/12 09:42
     */
    @ExceptionHandler(value = Exception.class)
    public R exceptionHandler(Exception e) {
        logger.error("未知异常，异常信息：" + e.getMessage());
        e.printStackTrace();
        return R.customError(ConstEnum.FAIL.getCode(),ConstEnum.FAIL.getMsg());
    }
}
