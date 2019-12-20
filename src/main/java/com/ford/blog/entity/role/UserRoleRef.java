package com.ford.blog.entity.role;

import com.ford.blog.entity.User;
import com.ford.blog.entity.base.BaseEntity;
import com.ford.blog.util.SnowflakeIdWorker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @ClassName: UserRoleRef
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 2:01
 **/
@Getter
@Setter
@Entity
@Table(name = "user_role")
@DynamicUpdate
public class UserRoleRef extends BaseEntity {
    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(userRoleId)) {
            userRoleId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }

    /**
     * 角色用户关系表
     */
    @Id
    @Column(name = "user_role_id", length = 32, updatable = false, unique = true)
    private String userRoleId;

    /**
     * 角色
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_role_user"))
    private Role role;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_role"))
    private User user;
}
