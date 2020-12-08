package com.definesys.rt.service;

import com.definesys.rt.bean.TDocument;
import com.definesys.rt.util.TUserException;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.List;

public interface DocumentService {

   //查询报告信息
   List<TDocument> findDocument(long userid) throws TUserException;

   //通过id分页查询
   Page<TDocument> findDocument(long userid,int page,int size) throws TUserException;

   //通过name分页查询
   Page<TDocument> findDocumentByName(String name,int page,int size) throws TUserException;

   //通过date分页查询
   Page<TDocument> findDocumentByUploaddate(String start, String end, int page,int size) throws ParseException;

   //通过id删除报告
   String deleteDocumentByid(long docuemntid) throws TUserException;

   //批量删除报告
   String deleteAllDocument(List<Long> documentids) throws TUserException;
}
