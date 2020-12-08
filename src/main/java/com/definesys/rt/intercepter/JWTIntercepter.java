package com.definesys.rt.intercepter;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.definesys.rt.util.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class JWTIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, Object> map = new HashMap<>();

        //从请求头中获取令牌
        String taken = request.getHeader("Authorization-token");

        try {
            JWTUtils.verify(taken);
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg","无效签名");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期");
        }catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg","token算法不一致");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("msg","token无效");
        }
        map.put("code",401);
        //将map转换成json jackson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
