package com.definesys.rt.service;

import com.definesys.rt.bean.RegisterRequest;
import com.definesys.rt.util.TUserException;

public interface RegisterService {

    //注册
    String register(RegisterRequest u) throws TUserException;
}
