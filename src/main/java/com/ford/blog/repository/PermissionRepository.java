package com.ford.blog.repository;

import com.ford.blog.entity.role.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName: PermissionRepository
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 3:46
 **/
public interface PermissionRepository extends JpaRepository<Permission,String>, JpaSpecificationExecutor<Permission> {

    Permission findByPermissionCode(String permissionCode);

    Permission findByPermissionCodeAndPermissionIdNot(String permissionCode, String permissionId);

    List<Permission> findByParentPermissionIsNull();

}
