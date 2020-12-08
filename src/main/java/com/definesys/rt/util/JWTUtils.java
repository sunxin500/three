package com.definesys.rt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static final String SING = "!QWERASDF~@^";
    /**
     * 生成token header.payload.sing
     */
    public static String getToken(Map<String,String> map){

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,60); //默认7天过期

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v) -> {
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime()) //指令过期时间
                .sign(Algorithm.HMAC256(SING));//sign

        return token;
    }

    /**
     * 验证token  合法性
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /**
     * 获取token 信息方法
     */
//    public static DecodedJWT getTokenInfo(String token){
//        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
//        verify.getClaim("user").asString();
//    }


}
