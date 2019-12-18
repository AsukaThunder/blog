package com.ford.blog.entity;

import com.ford.blog.entity.base.BaseEntity;
import com.ford.blog.util.SnowflakeIdWorker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @ClassName: Role
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/18 下午 5:55
 **/
@Setter
@Getter
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
     * 角色用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_user_role"))
    private User user;

}
