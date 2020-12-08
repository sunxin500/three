package com.definesys.rt.service.Impl;

import com.definesys.rt.bean.TDocument;
import com.definesys.rt.bean.TUser;
import com.definesys.rt.dao.TDocumentDao;
import com.definesys.rt.dao.TUserDao;
import com.definesys.rt.service.UploadService;
import com.definesys.rt.util.StatusCodeUtil;
import com.definesys.rt.util.TUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.zip.DataFormatException;


@Service
public class UploadServiceImpl implements UploadService {

    @Value("${file.store.path}")
    private String fileStorePath;

    @Autowired
    private TDocumentDao dao;

    @Autowired
    private TUserDao dao2;

    @Override
    public String upload(MultipartFile file,long userid) throws TUserException{
//        if (!file.isEmpty()){
//            //给图片名称赋值
////          fileName = UUID.randomUUID().toString();
//            //获取文件名字
//            String fileName = file.getOriginalFilename();
//            Path filePath = Paths.get(fileStorePath,fileName.substring(0,1),fileName);
//            TDocument tDocument = new TDocument();
//            tDocument.setDocumentname(fileName);
//            tDocument.setPath(filePath.getParent().toString());
//            tDocument.setUserid(userid);
//            TUser user = dao2.findTUserByUserid(userid);
//            tDocument.setUploaddate(new Date());
//            tDocument.setVersionNumber("1");
//            tDocument.setCreationDate(new Date());
//            tDocument.setCreateBy(user.getName());
//            tDocument.setLastUpdateDate(new Date());
//            tDocument.setLastUpdateBy(user.getName());
//            dao.save(tDocument);
//            try {
//                File fir = new File(fileStorePath);
//                if (!fir.exists()) {
//                    fir.mkdirs();
//                }
//                //创建文件
//                if (!Files.exists(filePath.getParent())){
//                    Files.createDirectory(filePath.getParent());
//                }
//                //复制文件到指定文件下
//                Files.copy(file.getInputStream(),filePath);
//                return "文件上传成功";
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new TUserException(StatusCodeUtil.EXCEPTION_ERROR,"文件上传失败");
//            }
//        }
        return "文件为空";
    }
}
