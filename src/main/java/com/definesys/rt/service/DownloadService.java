package com.definesys.rt.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

public interface DownloadService {

    //下载报告
    ResponseEntity download(long documentid) throws Exception;

    String download1(HttpServletResponse response,long documentid) throws Exception;

}
