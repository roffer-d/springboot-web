package com.roffer.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dulongfei
 * @description 生成登录签名token
 * @date 2022/4/18 16:53
 */
public class TokenUtils {
    /**
     * @description 设置过期时间：1天
     * @author Dulongfei
     * @date 2022/4/18 16:54
     */
    private static final long EXPIRE_DATE = 1000 * 60 * 60 * 24;
    /**
     * @description token秘钥
     * @author Dulongfei
     * @date 2022/4/18 16:53
     */
    private static final String TOKEN_SECRET = "ZCEQIUBFKSJBFJH2020BQWE";

    /**
     * @description 生成token
     * @params: account(String): 账号
     * password(String): 密码
     * @author Dulongfei
     * @date 2022/4/18 16:59
     */
    public static String generator(String id) {
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("id", id).withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    /**
     * @description 验证token
     * @params: token(String): token值
     * @author Dulongfei
     * @date 2022/4/18 16:59
     */
    public static boolean verify(String token) {
        try {
            token = token.replaceAll("Bearer ", "");
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
      * @description 从token中获取id
      * @params:
      *   token(String): token串
      * @author Dulongfei
      * @date 2022/4/19 16:10
      */
    public static String getIdFromToken(String token){
        String id = "";

        try {
            token = token.replaceAll("Bearer ", "");
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            if (jwt != null) {
                id = jwt.getClaim("id").asString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }
}
