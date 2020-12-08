package com.definesys.rt.Test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@Service
//@FeignClient(name = "osb" ,url = "http://osb.smec-cn.com/")
@FeignClient(name = "mvc" ,url = "http://192.168.201.161/9999/cxf/user/")
public interface DemoService {

//    @PostMapping(value = "RFC/Z_RFC_HR_GET_TELEPHONE/Z_RFC_HR_GET_TELEPHONEService")
//    public String demo(Map<String,Object> params);

    @GetMapping("/getjson/{id}")
    public String json(@PathVariable long id);

}
