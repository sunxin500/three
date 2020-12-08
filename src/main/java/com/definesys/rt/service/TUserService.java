package com.definesys.rt.service;

import com.definesys.rt.bean.TUser;
import com.definesys.rt.util.TUserException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TUserService {

    //通过名字查询用户
    TUser findTUserByName(String name);

    //通过名字查询用户
    List<TUser> findTUsersByName(String name);

    //通过角色分页查询用户
    Page<TUser> findTUserByRole(String role, int page, int size);

    //查询所有用户
    Page<TUser> findAllTUser(int page, int size);

    //删除用户
    void deleteTUserById(long id);

    //批量删除用户
    String deleteAllTUser(List<Long> ids);

    //修改用户信息
    String updateTUser(String name,String role,long userid);

    //修改密码
    String modifyPassword(String username,String password);
}
