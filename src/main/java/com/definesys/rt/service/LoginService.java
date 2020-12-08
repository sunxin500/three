package com.definesys.rt.service;

import com.definesys.rt.bean.LoginRequest;
import com.definesys.rt.bean.TUser;
import com.definesys.rt.util.TUserException;

public interface LoginService {

    //登录
    TUser login(LoginRequest u)  throws TUserException;
}
