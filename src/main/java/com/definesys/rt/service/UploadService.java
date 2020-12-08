package com.definesys.rt.service;

import com.definesys.rt.util.TUserException;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    //上传文件
    String upload(MultipartFile file,long userid) throws TUserException;
}
