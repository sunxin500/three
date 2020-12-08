package com.definesys.rt.service.Impl;

import com.definesys.rt.bean.LoginRequest;
import com.definesys.rt.bean.TUser;
import com.definesys.rt.dao.TUserDao;
import com.definesys.rt.service.LoginService;
import com.definesys.rt.util.StatusCodeUtil;
import com.definesys.rt.util.TUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TUserDao dao;

    @Override
    public TUser login(LoginRequest u) throws TUserException {
        TUser user = dao.findTUserByUsername(u.getUsername());
        if (user == null){
            throw new TUserException(StatusCodeUtil.EXCEPTION_USERNAME,"用户不存在");
        }
        if(!u.getPassword().equals(user.getPassword())){
            throw new TUserException(StatusCodeUtil.EXCEPTION_PASSWORD,"密码不正确");
        }
        return user;
    }
}
