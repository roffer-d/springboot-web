package com.roffer.web.config;

import com.google.gson.Gson;
import com.roffer.common.http.ConstEnum;
import com.roffer.common.http.R;
import com.roffer.common.utils.RedisUtils;
import com.roffer.common.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dulongfei
 * @description token验证拦截器
 * @date 2022/4/19 09:11
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestMethod = "OPTIONS";
        if (StringUtils.equals(requestMethod, request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)) {
            String redisKey = token.replaceAll("Bearer ", "");
            if (TokenUtils.verify(token) && null != redisUtils.get(redisKey)) {
                return true;
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(ConstEnum.SUCCESS.getCode());

        R r = R.customError(ConstEnum.NOT_LOGIN.getCode(),ConstEnum.NOT_LOGIN.getMsg());
        response.getWriter().append(new Gson().toJson(r));

        return false;
    }
}
