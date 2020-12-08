package com.definesys.rt.controller;

import com.definesys.rt.bean.TDocument;
import com.definesys.rt.bean.TUser;
import com.definesys.rt.dao.TDocumentDao;
import com.definesys.rt.dao.TUserDao;
import com.definesys.rt.dto.UploadResponseDTO;
import com.definesys.rt.service.AttchmentService;
import com.definesys.rt.util.Message;
import com.definesys.rt.util.MessageUtil;
import com.definesys.rt.util.StatusCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping(value = "/attchment")
@Api(tags = "附件模块")
public class AttchmentController{

    private final AttchmentService attchmentService;

    private static final Logger logger = LoggerFactory.getLogger(AttchmentController.class);

    @Autowired
    private TUserDao dao;

    @Autowired
    private TDocumentDao dao2;

    @Autowired
    public AttchmentController(AttchmentService attchmentService) {
        this.attchmentService = attchmentService;
    }

    @ApiOperation(value = "附件上传接口", notes = "")
    @PostMapping("/files/upload")
    public Message<UploadResponseDTO> upload(@RequestParam MultipartFile file,long userid) {
        try {
            UploadResponseDTO uploadFile = attchmentService.upload(file, "808864201255065616");
            String fileName = file.getOriginalFilename();
            TUser user = dao.findTUserByUserid(userid);
            TDocument tDocument = new TDocument();
            tDocument.setDocumentname(fileName);
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
            return MessageUtil.success(uploadFile);
        } catch (Exception e) {
            logger.error(e.toString());
            return MessageUtil.error(StatusCodeUtil.EXCEPTION_ERROR,e.getMessage());
        }
    }

    @ApiOperation(value = "附件下载接口", notes = "")
    @GetMapping("/files/download")
    @ResponseBody
    public Object download(@RequestParam(value = "dId") String dId,
                           @RequestParam(value = "dDocName") String dDocName) {
        try {
            return attchmentService.download(dId, dDocName);
        } catch (Exception e) {
            logger.error(e.toString());
            return MessageUtil.error(StatusCodeUtil.EXCEPTION_ERROR,e.getMessage());
        }
    }

    @ApiOperation(value = "附件预览接口", notes = "")
    @GetMapping("/files/preview")
    @ResponseBody
    public Object preview(@RequestParam(value = "dId") String dId,
                           @RequestParam(value = "dDocName") String dDocName) {
        try {
            return attchmentService.preview(dId, dDocName);
        } catch (Exception e) {
            logger.error(e.toString());
            return MessageUtil.error(StatusCodeUtil.EXCEPTION_ERROR,e.getMessage());
        }
    }

}
