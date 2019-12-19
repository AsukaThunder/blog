package com.ford.blog.entity;

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
     * 角色主键
     */
    @Id
    @Column(name = "id", nullable = false,length = 32, updatable = false, unique = true)
    private String roleId;
    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 关联用户角色表
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", orphanRemoval = true)
    private List<UserRole> userRoles;


}
