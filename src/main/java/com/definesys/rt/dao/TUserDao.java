package com.definesys.rt.dao;

import com.definesys.rt.bean.TUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TUserDao extends JpaRepository<TUser,Long> , JpaSpecificationExecutor<TUser> {

    //通过用户名查询用户
    TUser findTUserByUsername(String username);

    //通过id查用户
    TUser findTUserByUserid(long userid);

    //通过name查用户
    TUser findTUserByName(String name);

    List<TUser> findTUsersByName(String name);

    /**
     * 级联删除
     * @param userid
     */
    @Modifying
    @Transactional
    @Query(value = "delete from T_USER  where userid in (?1)",nativeQuery = true)
    void deleteTUsersByUseridIn(List<Long> userid);

    /**
     * 修改用户信息
     * @param name 姓名
     * @param role  角色
     * @param userid  用户id
     */
    @Transactional
    @Modifying
    @Query(value = "update T_USER set name=?1,role =?2 where userid=?3",nativeQuery = true)
    void updateTUser(String name,String role,long userid);

    @Transactional
    @Modifying
    @Query(value = "update T_USER set PASSWORD=?2 where USERNAME=?1",nativeQuery = true)
    void modifyPassword(String username,String password);
}
