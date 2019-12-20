package com.ford.blog.controller.login.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: DetailLoginDTO
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 3:55
 **/
@Getter
@Setter
public class DetailLoginDTO {
    private String userId;

    private String username;

    private String realName;

    private String mobilePhone;

    private Boolean isUse;

    private Integer gender;

    private String email;

    private String token;

    private Boolean isAdmin;

    private List<DetailUserRoleDTO> roles;

    private List<DetailLoginPermissionDTO> permissions;
    public User convertTo() {
        DetailUserConverter converter = new DetailUserConverter();
        return converter.convert(this);
    }

    public DetailLoginDTO convertFrom(User user) {
        DetailUserConverter converter = new DetailUserConverter();
        return converter.reverse().convert(user);
    }
}
