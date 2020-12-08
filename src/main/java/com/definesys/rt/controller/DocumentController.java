package com.definesys.rt.controller;


import com.definesys.rt.bean.TDocument;
import com.definesys.rt.service.DocumentService;
import com.definesys.rt.util.Message;
import com.definesys.rt.util.MessageUtil;
import com.definesys.rt.util.StatusCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@Api(tags = "报告信息模块")
public class DocumentController {

    @Autowired
    private DocumentService service;

    @GetMapping("/findDocument")
    @ApiOperation(value = "查询报告信息")
    public Message<List<TDocument>> findDocument(long userid){
        List<TDocument> documents = service.findDocument(userid);
        return MessageUtil.success(documents);
    }

    @GetMapping("/findDocumentByPage")
    @ApiOperation(value = "分页查询报告信息")
    public Message<Page<TDocument>> findDocument(long userid,int page, int size){
        Page<TDocument> pages = service.findDocument(userid,page, size);
        return MessageUtil.success(pages);
    }

    @GetMapping("/findDocumentByName")
    @ApiOperation(value = "通过姓名分页查询报告信息")
    public Message<Page<TDocument>> findDocumentByName(String name,int page, int size){
        Page<TDocument> pages = service.findDocumentByName(name,page, size);
        return MessageUtil.success(pages);
    }

    @GetMapping("/findDocumentByDate")
    @ApiOperation(value = "通过上传日期分页查询报告信息")
    public Message<Page<TDocument>> findDocumentByDate(String start, String end, int page, int size) throws ParseException {
        Page<TDocument> pages = service.findDocumentByUploaddate(start, end, page, size);
        return MessageUtil.success(pages);
    }

    @PostMapping("/deleteDocumentByid")
    @ApiOperation(value = "删除报告")
    public Message<String> deleteDocumentByid(long documentid){
        String s = service.deleteDocumentByid(documentid);
        return MessageUtil.success(s);
    }

    @PostMapping("/deleteAllDocument")
    @ApiOperation(value = "批量删除报告")
    public Message<String> deleteAllDocument(@RequestParam("documentids") List<Long> documentids){
        String msg = service.deleteAllDocument(documentids);
        return MessageUtil.success(msg);
    }
}
