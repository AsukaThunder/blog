package com.ford.blog.service.Impl;

import com.ford.blog.entity.User;
import com.ford.blog.entity.role.Permission;
import com.ford.blog.entity.role.Role;
import com.ford.blog.entity.role.RolePermissionRef;
import com.ford.blog.entity.role.UserRoleRef;
import com.ford.blog.exception.ResourceNotFoundException;
import com.ford.blog.repository.PermissionRepository;
import com.ford.blog.service.PermissionService;
import com.ford.blog.util.ErrorCode;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: PermissionServiceImpl
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 3:46
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public void addPermission(Permission permission) {
        if (null != permission.getParentPermission()) {
            Permission parent = this.findById(permission.getParentPermission().getPermissionId());
            permission.setParentPermission(parent);
        }
        permissionRepository.save(permission);
    }

    @Override
    public Permission findById(String permissionId) {
        return permissionRepository.findById(permissionId).orElse(null);
    }

    @Override
    public void updatePermission(Permission dbPermission, Permission newPermission) {
        dbPermission.setDescription(newPermission.getDescription());
        dbPermission.setEnable(newPermission.getEnable());
        dbPermission.setDisplaySort(newPermission.getDisplaySort());
        dbPermission.setPermissionName(newPermission.getPermissionName());
        dbPermission.setResourceUrl(newPermission.getResourceUrl());
        dbPermission.setPlatform(newPermission.getPlatform());
        dbPermission.setResourceType(newPermission.getResourceType());
        permissionRepository.save(dbPermission);
    }

    /**
     * 禁用权限及其子权限
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void disablePermission(Permission dbPermission) {
        Permission permission = getRecursivePermission(dbPermission, false);
        permissionRepository.save(permission);
    }

    /**
     * 启用权限及其子权限
     */
    @Override
    public void enablePermission(Permission dbPermission) {
        Permission permission = getRecursivePermission(dbPermission, true);
        permissionRepository.save(permission);
    }

    /**
     * 递归禁用、启用
     */
    private Permission getRecursivePermission(Permission permission, Boolean enable) {
        permission.setEnable(enable);
        if (CollectionUtils.isNotEmpty(permission.getSonPermissions())) {
            for (Permission son : permission.getSonPermissions()) {
                if (son.getCanDelete()) {
                    son.setEnable(enable);
                }
                // 递归
                this.getRecursivePermission(son, enable);
            }
        }
        return permission;
    }

    @Override
    public void deleteById(String permissionId) {
        Permission permission = permissionRepository.findById(permissionId).orElse(null);
        if (null == permission) {
            throw new ResourceNotFoundException(ErrorCode.EntityNotFound);
        }
        permissionRepository.deleteById(permissionId);
    }

    @Override
    public Boolean isPermission(User user, String permissionCode) {
        Boolean isAdmin = false;
        Permission userPermission = permissionRepository.findByPermissionCode(permissionCode);
        if (userPermission != null){
            List<RolePermissionRef> rolePermissionRef = userPermission.getRolePermissionRef();
            if (!org.springframework.util.CollectionUtils.isEmpty(rolePermissionRef)){
                for (RolePermissionRef permissionRef : rolePermissionRef) {
                    Role role = permissionRef.getRole();
                    List<UserRoleRef> userRoleRef = user.getUserRoleRef();
                    for (UserRoleRef roleRef : userRoleRef) {
                        if (role.getRoleCode().equals(roleRef.getRole().getRoleCode())) {
                            isAdmin = true;
                        }
                    }
                }
            }
        }
        return isAdmin;
    }

    @Override
    public List<Permission> getPermissionTree() {
        return permissionRepository.findByParentPermissionIsNull();
    }

    @Override
    public Permission findByCode(String permissionCode) {
        return permissionRepository.findByPermissionCode(permissionCode);
    }

    @Override
    public Permission findByCode(String permissionCode, String permissionId) {
        return permissionRepository.findByPermissionCodeAndPermissionIdNot(permissionCode, permissionId);
    }
}
