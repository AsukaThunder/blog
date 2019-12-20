package com.ford.blog.service;

import com.ford.blog.entity.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ClassName: RoleService
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 6:25
 **/
public interface RoleService {
    /**
     * 查找所有角色
     */
    List<Role> getAllRoles();

    /**
     * 角色页面
     */
    Page<Role> getRolePage(Pageable pageable, String roleName, String roleCode);

    /**
     * 查找单个角色
     */
    Role findOne(String roleId);

    /**
     * 查找单个角色
     */
    Role getByUserId(String userId);

    /**
     * 添加角色
     */
    void addRole(Role role);

    /**
     * 更新角色信息
     */
    void updateRole(Role role, String roleId);

    /**
     * 删除一个角色
     */
    void deleteById(String roleId);
}
