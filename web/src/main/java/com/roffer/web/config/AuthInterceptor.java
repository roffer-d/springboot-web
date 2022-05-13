package com.roffer.web.config;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.roffer.common.http.ConstEnum;
import com.roffer.common.http.R;
import com.roffer.common.utils.RedisUtils;
import com.roffer.common.utils.TokenUtils;
import com.roffer.web.enums.RedisConstEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Dulongfei
 * @description token验证拦截器
 * @date 2022/4/19 09:11
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

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
            String userId = TokenUtils.getIdFromToken(token);
            String redisKey = RedisConstEnum.USER.getValue() + userId;

            if (TokenUtils.verify(token) && redisUtils.hasKey(redisKey)) {
                boolean hasAuth = false;
                String code = request.getHeader("code");
                String auth = request.getHeader("auth");

                Map<String,Object> userMap = redisUtils.get(redisKey,Map.class);
                List<Map> roleAuthList = (List)userMap.get("role");

                for(Map roleAuth : roleAuthList){
                    String _code = String.valueOf(roleAuth.get("code"));
                    String _auth = String.valueOf(roleAuth.get("auth"));
                    List<String> authList = Arrays.asList(_auth.split(","));

                    if(code.equals(_code) && authList.contains(auth)){
                        hasAuth = true;
                        break;
                    }
                }

                if(!hasAuth){
                    /** 无数据操作权限 **/

                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    response.setStatus(ConstEnum.SUCCESS.getCode());

                    R r = R.customError(ConstEnum.NO_AUTH.getCode(), ConstEnum.NO_AUTH.getMsg());
                    response.getWriter().append(new Gson().toJson(r));
                }

                return hasAuth;
            }else{
                /** 登录失效 **/

                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.setStatus(ConstEnum.SUCCESS.getCode());

                R r = R.customError(ConstEnum.NOT_LOGIN.getCode(), ConstEnum.NOT_LOGIN.getMsg());
                response.getWriter().append(new Gson().toJson(r));
            }
        }

        return false;
    }
}
