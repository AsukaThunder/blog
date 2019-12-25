package com.ford.blog.entity;

import com.ford.blog.entity.base.BaseEntity;
import com.ford.blog.entity.role.RoleCode;
import com.ford.blog.entity.role.UserRoleRef;
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
@Getter
@Setter
@Entity
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
    @Column(name = "account_name", nullable = false)
    private String userName;
    /**
     * 真实姓名
     */
    @Column(name = "real_name", length = 30)
    private String realName;
    /**
     * 性别
     */
    @Column(name = "gender")
    private Integer gender;
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
    @Column(name = "isUse")
    private Boolean isUse;
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
    @Column(name = "user_face")
    private String userFace;
    /**
     * 用户角色关系
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<UserRoleRef> userRoleRef;
    /**
     * 用户评论关系
     */
    @OneToMany(mappedBy = "user")
    private List<Comments> comments;
    /**
     * 用户文章关联
     */
    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    public List<UserRoleRef> getUserRoleRef() {
        return userRoleRef;
    }

    public void setUserRoleRef(List<UserRoleRef> userRoleRef) {
        this.userRoleRef = userRoleRef;
    }
    /**
     * 是否超级管理员
     */
    @Transient
    public Boolean isRoot() {
        return this.isCurrentRole(RoleCode.ROOT.name());
    }

    /**
     * 是否管理员
     */
    @Transient
    public Boolean isAdmin() {
        return this.isCurrentRole(RoleCode.ADMIN.name());
    }
    private Boolean isCurrentRole(String roleCode) {
        List<UserRoleRef> roleRefs = this.getUserRoleRef();
        for (UserRoleRef ref : roleRefs) {
            if (roleCode.equals(ref.getRole().getRoleCode())) {
                return true;
            }
        }
        return false;
    }
}
