package com.roffer.web.config;

import com.roffer.common.utils.TokenUtils;
import com.roffer.web.annotation.LogRecords;
import com.roffer.web.modules.sys.entity.BasicLog;
import com.roffer.web.modules.sys.entity.BasicUser;
import com.roffer.web.modules.sys.service.BasicLogService;
import com.roffer.web.modules.sys.service.BasicUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Roffer
 * @description 操作日志记录
 * @date 2022/5/12 10:30
 */
@Aspect
@Component
public class LogAop {
    private Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Resource
    private BasicLogService basicLogService;

    @Resource
    private BasicUserService basicUserService;

    /**
     * 定义切入点
     */
    @Pointcut("@annotation( com.roffer.web.annotation.LogRecords )")
    public void OperLogs() {}

    @Before(value = "OperLogs()")
    public void logBefore(JoinPoint joinPoint){
        try {
            //获取当前类象
            Class<?> clazz = joinPoint.getTarget().getClass();
            // 获取当前类的执行的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = clazz.getMethod(signature.getMethod().getName(), signature.getMethod().getParameterTypes());

            // 获取注解里的参数
            LogRecords log = method.getAnnotation(LogRecords.class);

            // 获取请求参数
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //String param = JSON.toJSON(new PageData(request)).toString();
            //获取请求路径
            String url = request.getRequestURL().toString();
            //获取操作描述内容
            String remark = log.remark();
            // 获取操作类型
            String action = log.action().name();

            // 获取操作人
            String token = request.getHeader("Authorization");
            String userId = TokenUtils.getIdFromToken(token);
            BasicUser user = basicUserService.getById(userId);

            BasicLog basicLog = new BasicLog();
            basicLog.setRemark(remark);
            basicLog.setUserId(userId);
            basicLog.setUserName(user.getName());
            basicLogService.save(basicLog);
        }catch (Exception e){
            logger.error("写入操作日志失败！", e.getMessage());
        }
    }
}
