package com.definesys.rt.service.Impl;

import com.definesys.rt.bean.TUser;
import com.definesys.rt.dao.TUserDao;
import com.definesys.rt.service.TUserService;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class TUserServiceImpl implements TUserService {

    Logger logger = LoggerFactory.getLogger(TUserServiceImpl.class);

    @Autowired
    private TUserDao dao;

    @Override
    public TUser findTUserByName(String name) {
        return dao.findTUserByName(name);
    }

    public List<TUser> findTUsersByName(String name) {
        return dao.findTUsersByName(name);
    }

    @Override
    public Page<TUser> findTUserByRole(String role, int page, int size) {
        Specification<TUser> specification = new Specification<TUser>() {
            @Override
            public Predicate toPredicate(Root<TUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ArrayList<Predicate> PredicateList = new ArrayList<>();
                Predicate p = criteriaBuilder.equal(root.get("role").as(String.class), role);
                PredicateList.add(p);
                return criteriaBuilder.and(PredicateList.toArray(new Predicate[0]));
            }
        };
        return dao.findAll(specification,PageRequest.of(page,size,Sort.Direction.ASC,"userid"));
    }

    @Override
    public Page<TUser> findAllTUser(int page, int size) {
        return dao.findAll(PageRequest.of(page,size, Sort.Direction.ASC,"userid"));
    }

    @Override
    public void deleteTUserById(long id) {
        dao.deleteById(id);
    }

    @Override
    public String deleteAllTUser(List<Long> ids) {
        try {
            dao.deleteTUsersByUseridIn(ids);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @Override
    public String updateTUser(String name,String role,long userid) {
        try {
            dao.updateTUser(name,role,userid);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return "fail";
        }
    }

    @Override
    public String modifyPassword(String username, String password) {
        dao.modifyPassword(username,password);
        return "修改成功";
    }
}
