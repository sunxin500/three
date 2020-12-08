package com.definesys.rt.controller;

import ch.qos.logback.core.status.StatusUtil;
import com.definesys.rt.bean.LoginRequest;
import com.definesys.rt.bean.RegisterRequest;
import com.definesys.rt.bean.TUser;
import com.definesys.rt.service.LoginService;
import com.definesys.rt.service.RegisterService;
import com.definesys.rt.util.Message;
import com.definesys.rt.util.MessageUtil;
import com.definesys.rt.util.StatusCodeUtil;
import com.definesys.rt.util.TUserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 注册
 */
@RestController
@Api(tags = "用户注册")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public Message<String> register(RegisterRequest u){
        String register = service.register(u);
        return MessageUtil.success(register);
    }
}
