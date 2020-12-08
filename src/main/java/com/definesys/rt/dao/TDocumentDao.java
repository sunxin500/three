package com.definesys.rt.dao;

import com.definesys.rt.bean.TDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TDocumentDao extends JpaRepository<TDocument,Long>, JpaSpecificationExecutor {

    //通过id查询报告信息
    TDocument findTDocumentByDocumentid(long documentid);

    //查询个人报告
    List<TDocument> findDocumenetByUserid(long userid);

    @Modifying
    @Transactional
    @Query(value = "delete from TDocument d where d.documentid in (:documentids)")
    void deleteTDocumentsByDocumentidin(List<Long> documentids);

    //查询所有报告
    @Query(value = "select * from T_DOCUMENT order by USERID",nativeQuery = true)
    List<TDocument> findAllDocumenet();
}

