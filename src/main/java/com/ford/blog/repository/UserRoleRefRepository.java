package com.ford.blog.repository;

import com.ford.blog.entity.role.UserRoleRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName: UserRoleRefRepository
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 4:10
 **/
public interface UserRoleRefRepository extends JpaRepository<UserRoleRef, String>, JpaSpecificationExecutor<UserRoleRef> {

    /**
     * 获取用户角色
     */
    List<UserRoleRef> getByUserId(String userId);
}
