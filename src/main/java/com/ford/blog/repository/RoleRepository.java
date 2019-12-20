package com.ford.blog.repository;

import com.ford.blog.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName: RoleRepository
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 6:32
 **/
public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

}
