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
 * @ClassName: User
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/18 下午 6:00
 **/
@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name = "user")
public class User extends BaseEntity {
    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(userId)) {
            userId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }

    /**
     * 用户主键
     */
    @Id
    @Column(name = "id", nullable = false,length = 32, updatable = false, unique = true)
    private String userId;
    /**
     * 用户账号
     */
    @Column(name = "account_name", length = 255, nullable = false)
    private String userName;
    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;
    /**
     * 用户密码
     */
    @Column(name = "password", length = 32, nullable = false)
    private String password;
    /**
     * 是否启用
     */
    @Column(name = "enabled")
    private Boolean enabled;
    /**
     * 邮箱
     */
    @Column(name = "email", length = 50)
    private String email;
    /**
     * 手机
     */
    @Column(name = "mobile_phone", length = 20)
    private String mobilePhone;
    /**
     * 用户头像
     */
    @Column(name = "user_face", length = 255)
    private String userFace;
    /**
     * 用户角色关系
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalUser", orphanRemoval = true)
    private List<Role> userRoles;
}
