package com.ford.blog.entity.role;

/**
 * @ClassName: RoleCode
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 3:37
 * 角色code
 **/
public enum RoleCode {

    /**
     * 管理员
     */
    ROOT("超级管理员"),

    ADMIN("管理员"),

    /**
     * 用户
     */
    BLOGGER("博主"),

    /**
     * 游客
     */
    VISITOR("游客");

    private String value;

    public String getValue() {
        return value;
    }

    RoleCode(String value) {
        this.value = value;
    }
}