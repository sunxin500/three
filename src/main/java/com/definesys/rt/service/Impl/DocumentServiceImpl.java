package com.definesys.rt.service.Impl;

import com.definesys.rt.bean.TDocument;
import com.definesys.rt.bean.TUser;
import com.definesys.rt.dao.TDocumentDao;
import com.definesys.rt.dao.TUserDao;
import com.definesys.rt.service.DocumentService;
import com.definesys.rt.util.TUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    private TDocumentDao dao;

    @Autowired
    private TUserDao dao2;

    @Override
    public List<TDocument> findDocument(long userid) {
        TUser user = dao2.findTUserByUserid(userid);
        if(user.getRole().equals("Superuser")) {
            return dao.findAllDocumenet();
        }else {
            return dao.findDocumenetByUserid(userid);
        }
    }

    @Override
    public Page<TDocument> findDocument(long userid,int page, int size) throws TUserException {
        TUser user = dao2.findTUserByUserid(userid);
        if(user.getRole().equals("管理员")) {
            return dao.findAll(PageRequest.of(page, size, Sort.Direction.DESC,"uploaddate"));
        }else {
            Specification<TDocument> specification = new Specification<TDocument>() {
                @Override
                public Predicate toPredicate(Root<TDocument> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    ArrayList<Predicate> PredicateList = new ArrayList<>();
                    Predicate p = criteriaBuilder.equal(root.get("userid").as(Long.class), userid);
                    PredicateList.add(p);
//                    Predicate[] predicates = new Predicate[PredicateList.size()];
//                    return query.where(PredicateList.toArray(predicates)).getRestriction();
                    return criteriaBuilder.and(PredicateList.toArray(new Predicate[0]));
                }
            };
            return dao.findAll(specification, PageRequest.of(page,size,Sort.Direction.DESC,"uploaddate"));
        }
    }

    @Override
    public Page<TDocument> findDocumentByName(String name, int page, int size) throws TUserException {
        Specification<TDocument> specification = new Specification<TDocument>() {
            @Override
            public Predicate toPredicate(Root<TDocument> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ArrayList<Predicate> PredicateList = new ArrayList<>();
                Predicate p = criteriaBuilder.equal(root.get("username").as(String.class), name);
                PredicateList.add(p);
                return criteriaBuilder.and(PredicateList.toArray(new Predicate[0]));
            }
        };
        return dao.findAll(specification, PageRequest.of(page,size, Sort.Direction.DESC,"uploaddate"));
    }

    @Override
    public Page<TDocument> findDocumentByUploaddate(String start, String end, int page, int size) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date starttime = sdf.parse(start);
        Date endtime = sdf.parse(end);
        logger.info(String.valueOf(starttime));
        logger.info(String.valueOf(endtime));
        Specification<TDocument> specification = new Specification<TDocument>() {
            @Override
            public Predicate toPredicate(Root<TDocument> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ArrayList<Predicate> PredicateList = new ArrayList<>();
                Predicate p = criteriaBuilder.between(root.get("uploaddate").as(Date.class),starttime,endtime);
                PredicateList.add(p);
                return criteriaBuilder.and(PredicateList.toArray(new Predicate[0]));
            }
        };
        return dao.findAll(specification, PageRequest.of(page,size, Sort.Direction.DESC,"uploaddate"));
    }

    @Override
    public String deleteDocumentByid(long docuemntid) throws TUserException{
        try {
            dao.deleteById(docuemntid);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @Override
    public String deleteAllDocument(List<Long> documentids) throws TUserException {
        try {
            dao.deleteTDocumentsByDocumentidin(documentids);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
