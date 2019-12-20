package com.ford.blog.service;

import com.ford.blog.entity.role.UserRoleRef;

import java.util.List;

/**
 * @ClassName: UserRoleService
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 4:08
 **/
public interface UserRoleService {
    /**
     * 根据UserId查询所有的关联的role信息
     */
    List<UserRoleRef> getByUserId(String userId);

}
