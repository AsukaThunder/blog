package com.ford.blog.service.Impl;

import com.ford.blog.entity.role.Role;
import com.ford.blog.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: RoleServiceImpl
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 6:31
 **/
@Service
public class RoleServiceImpl implements RoleService {
    public RoleServiceImpl() {
        super();
    }

    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public Page<Role> getRolePage(Pageable pageable, String roleName, String roleCode) {
        return null;
    }

    @Override
    public Role findOne(String roleId) {
        return null;
    }

    @Override
    public Role getByUserId(String userId) {
        return null;
    }

    @Override
    public void addRole(Role role) {

    }

    @Override
    public void updateRole(Role role, String roleId) {

    }

    @Override
    public void deleteById(String roleId) {

    }
}
