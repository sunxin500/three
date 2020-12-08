package com.definesys.rt.controller;

import com.definesys.rt.service.Impl.UploadServiceImpl;
import com.definesys.rt.util.Message;
import com.definesys.rt.util.MessageUtil;
import com.definesys.rt.util.StatusCodeUtil;
import com.definesys.rt.util.TUserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.font.MultipleMaster;

@RestController
@Api(tags = "上传文件模块")
public class UploadController {

    @Autowired
    private UploadServiceImpl service;

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Message<String> upload(@RequestParam("file") MultipartFile file, long userid) throws TUserException {
        try {
            String upload = service.upload(file, userid);
            return MessageUtil.success(upload);
        } catch (TUserException e) {
            e.printStackTrace();
            return MessageUtil.error(StatusCodeUtil.EXCEPTION_ERROR,e.getMessage());
        }
    }
}

