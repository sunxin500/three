package com.definesys.rt.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public class DemoServiceImpl {
    final
    DemoService demoService;

    @Autowired
    public DemoServiceImpl(DemoService demoService) {
        this.demoService = demoService;
    }

//    public String demo(Map<String,Object> params){
//        return demoService.demo(params);
//    }


}
