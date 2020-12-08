package com.definesys.rt.service.Impl;

import com.definesys.rt.bean.TDocument;
import com.definesys.rt.dao.TDocumentDao;
import com.definesys.rt.service.DownloadService;
import com.definesys.rt.util.MessageUtil;
import com.definesys.rt.util.StatusCodeUtil;
import com.definesys.rt.util.TUserException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private TDocumentDao dao;


    @Override
    public String download1(HttpServletResponse response, long documentid) throws Exception {
//        TDocument document = dao.findTDocumentByDocumentid(documentid);
//        if (document == null) {
//            throw new TUserException(StatusCodeUtil.EXCEPTION_ERROR, "文件不存在");
//        }
//        String filename = document.getDocumentname();
//        String downLoadPath = document.getPath();
//
//        File file = new File(downLoadPath);
//        if (file.exists()) {
//            response.setContentType("application/force-download");// 设置强制下载不打开
//            response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
//            byte[] buffer = new byte[1024];
//            FileInputStream fis = null;
//            BufferedInputStream bis = null;
//            try {
//                fis = new FileInputStream(file);
//                bis = new BufferedInputStream(fis);
//                OutputStream outputStream = response.getOutputStream();
//                int i = bis.read(buffer);
//                while (i != -1) {
//                    outputStream.write(buffer, 0, i);
//                    i = bis.read(buffer);
//                }
//
//                return "下载成功";
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (bis != null) {
//                    try {
//                        bis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (fis != null) {
//                    try {
//                        fis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
        return "下载失败";
    }

//    @Override
//    public String download1(HttpServletResponse response,long documentid) throws Exception {
//        TDocument document = dao.findTDocumentByDocumentid(documentid);
//        if (document == null) {
//            throw new TUserException(StatusCodeUtil.EXCEPTION_ERROR, "文件不存在");
//        }
//        String filename = document.getDocumentname();
//        String downLoadPath = document.getPath();
//        FileInputStream in = new FileInputStream(new File(downLoadPath, filename));
//        response.setHeader("Content-Dispostition","attachment;fileName"+ URLEncoder.encode(filename,"UTF-8"));
//        response.setContentType("image/jpeg");
//        ServletOutputStream out = response.getOutputStream();
//        IOUtils.copy(in,out);
//        IOUtils.closeQuietly(in);
//        IOUtils.closeQuietly(out);
//        return "下载成功";
//    }
    public ResponseEntity download(long documentid) throws Exception {
        TDocument document = dao.findTDocumentByDocumentid(documentid);
        if (document == null) {
            throw new TUserException(StatusCodeUtil.EXCEPTION_ERROR, "文件不存在");
        }
        String filename = document.getDocumentname();
        //处理一下要下载的文件名字,解决中文乱码
        String downFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        //获得下载文件所在路径 可以指向系统中的任意一个有权访问的路径
//        String downLoadPath = document.getPath();
//        FileSystemResource resource = new FileSystemResource(downLoadPath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Dispostition","attachment");
        headers.setContentDispositionFormData("attachment", downFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(resource.contentLength())
//                .body(new InputStreamResource(resource.getInputStream()));
        return null;

    }

//    @Override
//    public ResponseEntity download(long documentid) throws Exception {
//        TDocument document = dao.findTDocumentByDocumentid(documentid);
//        if (document == null) {
//            throw new TUserException(StatusCodeUtil.EXCEPTION_ERROR, "文件不存在");
//        }
//        String filename = document.getDocumentname();
//        //获得下载文件所在路径 可以指向系统中的任意一个有权访问的路径
//        String downLoadPath = document.getPath();
////        String downLoadPath = "E:\\projectFiles\\documenet";
//
//        //创建要下载的文件对象
//        File file = new File(downLoadPath, filename);
//
//        //处理一下要下载的文件名字,解决中文乱码
//        String downFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
//        //创建响应头信息的对象
//        HttpHeaders headers = new HttpHeaders();
//        //设置下载的响应头信息,通过浏览器响应正文的内容是用户要下载的,不用浏览器解析
//        headers.setContentDispositionFormData("attachment", downFileName);
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
//    }
}
