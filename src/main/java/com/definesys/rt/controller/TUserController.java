package com.definesys.rt.controller;

import com.definesys.rt.bean.TUser;
import com.definesys.rt.service.TUserService;
import com.definesys.rt.util.Message;
import com.definesys.rt.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "查询用户")
public class TUserController {

    Logger logger = LoggerFactory.getLogger(TUserController.class);

    @Autowired
    private TUserService service;

    @GetMapping("/findTUserByName")
    @ApiOperation(value = "通过名字查询用户")
    public Message<TUser> findTUserByName(String name){
        TUser user = service.findTUserByName(name);
        return MessageUtil.success(user);
    }

    @GetMapping("/findTUsersByName")
    @ApiOperation(value = "通过名字查询用户")
    public Message<List<TUser>> findTUsersByName(String name){
        List<TUser> users = service.findTUsersByName(name);
        return MessageUtil.success(users);
    }

    @GetMapping("/findTUsersByRole")
    @ApiOperation(value = "通过角色分页查询用户")
    public Message<Page<TUser>> findTUsersByRole(String role, int page, int size){
        Page<TUser> users = service.findTUserByRole(role, page, size);
        return MessageUtil.success(users);
    }

    @GetMapping("/findAllTUser")
    @ApiOperation(value = "查询所有用户")
    public Message<Page<TUser>> findAllTUser(int page,int size){
        Page<TUser> user = service.findAllTUser(page, size);
        return MessageUtil.success(user);
    }

    @PostMapping("/deleteTUserById")
    @ApiOperation(value = "删除用户")
    public Message deleteTUserById(long id){
        service.deleteTUserById(id);
        return MessageUtil.success();
    }

    @PostMapping("/deleteAllTUser")
    @ApiOperation(value = "批量删除用户")
    public Message<String> deleteAllTUser(@RequestParam("ids") List<Long> ids){
        String msg = service.deleteAllTUser(ids);
        return MessageUtil.success(msg);

    }

    @PostMapping("/updateTUser")
    @ApiOperation(value = "修改用户信息")
    public Message<String> updateTUser(String name,String role,long userid){
        String message = service.updateTUser(name, role, userid);
        return MessageUtil.success(message);
    }

    @PostMapping("/modifyPS")
    @ApiOperation(value = "修改用户信息")
    public Message<String> modifyPassword(String username,String password){
        String msg = service.modifyPassword(username, password);
        return MessageUtil.success(msg);
    }
}
