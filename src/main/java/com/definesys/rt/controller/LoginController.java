package com.definesys.rt.controller;

import com.definesys.rt.bean.LoginRequest;
import com.definesys.rt.bean.TUser;
import com.definesys.rt.service.LoginService;
import com.definesys.rt.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "登录模块")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    private LoginService service;

//    @GetMapping("/login")
//    @ApiOperation(value = "登录")
//    public Message<TUser> login(LoginRequest u) throws TUserException{
//        try {
//            TUser user = service.login(u);
//            return MessageUtil.success(user);
//        } catch (TUserException e) {
//            e.printStackTrace();
//            return MessageUtil.error(e.getCode(),e.getMessage());
//        }
//    }

    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public Message<Map<Object, Object>> login(LoginRequest u) throws TUserException{
        logger.info("用户名：[{}]",u.getUsername());
        logger.info("密码：[{}]",u.getPassword());
        try {
            HashMap<Object, Object> map = new HashMap<>();
            TUser user = service.login(u);
            Map<String,String> payload = new HashMap<>();
            payload.put("username",user.getUsername());
            payload.put("password",user.getPassword());
            String token = JWTUtils.getToken(payload);
            map.put("token",token);
            map.put("userid",String.valueOf(user.getUserid()));
            map.put("name",user.getName());
            map.put("role",user.getRole());
            return MessageUtil.success(map);
        } catch (TUserException e) {
            e.printStackTrace();
            return MessageUtil.error(e.getCode(),e.getMessage());
        }
    }

//    @PostMapping("/test")
//    public Map<String, Object> test(){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("state",true);
//        map.put("msg","请求成功");
//        return map;
//    }
}
