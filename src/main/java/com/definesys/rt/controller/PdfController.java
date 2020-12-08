package com.definesys.rt.controller;

import com.definesys.rt.bean.TDocument;
import com.definesys.rt.bean.TUser;
import com.definesys.rt.dao.TDocumentDao;
import com.definesys.rt.dao.TUserDao;
import com.definesys.rt.dto.UploadResponseDTO;
import com.definesys.rt.service.AttchmentService;
import com.definesys.rt.service.Impl.SendSmsImpl;
import com.definesys.rt.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

@RestController
@RequestMapping(value = "/pdf")
@Api(tags = "上传pdf文档")
public class PdfController {

    private AttchmentService service;

    @Autowired
    private TUserDao dao;

    @Autowired
    private TDocumentDao dao2;

    @Autowired
    private SendSmsImpl sms;

    @Autowired
    public PdfController(AttchmentService service) {
        this.service = service;
    }

    @PostMapping("/created")
    @ApiOperation(value = "创建上传pdf文档")
    public Message<String> createPdf(String docName,String start, String end, String today, String name, String num, String desc,long userid) throws Exception {
        if (!StringUtil.isEmpty(docName,start,end,today,name,name,desc)){
            docName = StringUtil.strAppend(docName,".pdf");
        }else {
            return MessageUtil.error(StatusCodeUtil.EXCEPTION_ERROR,"文件参数为空");
        }
        try {
                File file = PdfRequest.createPDF(start, end, today, name, num, desc);
                FileInputStream fileInputStream = new FileInputStream(file);
                MultipartFile multipartFile = new MockMultipartFile(docName,null, ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
                System.out.println(multipartFile.getOriginalFilename());
                UploadResponseDTO uploadFile = service.upload(multipartFile, "808864201255065616");
                TUser user = dao.findTUserByUserid(userid);
                TDocument tDocument = new TDocument();
                tDocument.setDocumentname(docName);
                tDocument.setDdocname(uploadFile.getdDocName());
                tDocument.setDid(uploadFile.getdId());
                tDocument.setUserid(user.getUserid());
                tDocument.setUsername(user.getName());
                tDocument.setUploaddate(new Date());
                tDocument.setVersionNumber("1");
                tDocument.setCreationDate(new Date());
                tDocument.setCreateBy(user.getName());
                tDocument.setLastUpdateDate(new Date());
                tDocument.setLastUpdateBy(user.getName());
                dao2.save(tDocument);

                //成功上传发送提示短信
//            HashMap<String, Object> map = new HashMap<>();
                //上一个月
//            int month = Calendar.getInstance().get(Calendar.MONTH);
//            map.put("code",user.getName()+"已上传"+month);
//            sms.send("15383643806","SMS_205409402",map);

                //删除临时生成的pdf文档
//            file.deleteOnExit();
                return MessageUtil.success("上传成功");
        }catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error(StatusCodeUtil.EXCEPTION_ERROR,e.getMessage());
        }
    }
}

