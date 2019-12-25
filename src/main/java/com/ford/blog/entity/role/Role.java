package com.ford.blog.entity.role;

import com.ford.blog.entity.base.BaseEntity;
import com.ford.blog.util.SnowflakeIdWorker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName: Role
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/18 下午 5:55
 **/
@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "role")
public class Role extends BaseEntity {
    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(roleId)) {
            roleId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }

    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id", length = 32, updatable = false, unique = true)
    private String roleId;

    /**
     * 角色编码
     */
    @Column(name = "role_code", length = 32)
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 是否启用
     */
    @Column(name = "enable", length = 1)
    private Boolean enable;

    /**
     * 能否删除: 超级管理员
     */
    @Column(name = "can_delete", length = 1)
    private Boolean canDelete;
    /**
     * 角色权限表
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RolePermissionRef> rolePermissionRef;
    /**
     * 关联用户角色表
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", orphanRemoval = true)
    private List<UserRoleRef> userRoleRefs;


}
