package com.ford.blog.entity.role;

import com.ford.blog.entity.base.BaseEntity;
import com.ford.blog.util.SnowflakeIdWorker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @ClassName: RolePermissionRef
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 3:35
 * 角色权限关联表
 **/
@Getter
@Setter
@Entity
@Table(name = "role_permission_ref")
@DynamicUpdate
public class RolePermissionRef extends BaseEntity {

    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(rolePermissionId)) {
            rolePermissionId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }
    @Id
    @Column(name = "role_permission_id", length = 32, updatable = false, unique = true)
    private String rolePermissionId;

    /**
     * 博客角色
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk1_role_permission_ref"))
    private Role role;

    /**
     * 博客权限
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "permission_id", foreignKey = @ForeignKey(name = "fk2_role_permission_ref"))
    private Permission permission;

}
