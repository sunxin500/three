package com.definesys.rt.controller;

import com.definesys.rt.service.SendSms;
import com.definesys.rt.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin  //跨域支持
public class SendSmsController {

    @Autowired
    private SendSms sendSms;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("/send")
    public String code(@RequestParam("phone") String phone,String number){
        //如何验证码为空  就需要发送短信
        if (StringUtils.isEmpty(number)) {
            //生成验证码并存入redis
            String code = UUIDUtil.getIntegerUUID(6);
            HashMap<String, Object> map = new HashMap<>();
            map.put("code",code);
            boolean isSend = sendSms.send(phone, "SMS_205134718", map);

            if (isSend) {
                redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
                return "发送成功";
            }else {
                return "发送失败";
            }
        }else {
            //如何验证码不为空  就去Redis缓存数据库验证
            //调用发送方法 (模拟redis)
            String code = (String) redisTemplate.opsForValue().get(phone);
            if (code.equals(number)){
                return "已存在";
            }else {
                return "验证码错误";
            }
        }
    }

}

