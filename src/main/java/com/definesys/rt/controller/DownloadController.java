package com.definesys.rt.controller;

import com.definesys.rt.service.DownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "下载报告模块")
public class DownloadController {

    @Autowired
    private DownloadService service;

    @GetMapping("/download")
    @ApiOperation(value = "下载报告")
    public ResponseEntity downLoadDocument(HttpServletResponse response,long documentid) throws Exception {
        return service.download(documentid);
    }
    @GetMapping("/download1")
    @ApiOperation(value = "下载报告")
    public String download(HttpServletResponse response,long documentid) throws Exception{
        return service.download1(response,documentid);
    }
}
