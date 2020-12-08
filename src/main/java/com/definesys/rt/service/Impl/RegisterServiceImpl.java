package com.definesys.rt.service.Impl;

import com.definesys.rt.bean.RegisterRequest;
import com.definesys.rt.bean.TUser;
import com.definesys.rt.dao.TUserDao;
import com.definesys.rt.service.RegisterService;
import com.definesys.rt.util.StatusCodeUtil;
import com.definesys.rt.util.TUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private TUserDao dao;

    @Override
    public String register(RegisterRequest u) throws TUserException{
        try {
            TUser user = dao.findTUserByUsername(u.getUsername());
            if (user != null){
                throw new TUserException(StatusCodeUtil.EXCEPTION_ERROR,"用户名已存在！");
            }
            TUser tUser = new TUser();
            tUser.setUsername(u.getUsername());
            tUser.setPassword(u.getPassword());
            tUser.setName(u.getName());
            tUser.setRole("普通用户");
            tUser.setVersionNumber("1");
            tUser.setCreationDate(new Date());
            tUser.setCreateBy(u.getName());
            tUser.setLastUpdateDate(new Date());
            tUser.setLastUpdateBy(u.getName());
            dao.save(tUser);
            return "ok";
        } catch (TUserException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
