package com.ford.blog.controller.user.dto;

import com.ford.blog.entity.role.UserRoleRef;
import com.google.common.base.Converter;

/**
 * @ClassName: DetailUserRoleDTO
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 5:33
 **/
public class DetailUserRoleDTO {
    private String userRoleId;

    private String userId;

    private String roleId;

    private String roleCode;

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public UserRoleRef convertTo() {
        DetailUserRoleConverter converter = new DetailUserRoleConverter();
        return converter.convert(this);
    }

    public DetailUserRoleDTO convertFrom(UserRoleRef ref) {
        DetailUserRoleConverter converter = new DetailUserRoleConverter();
        return converter.reverse().convert(ref);
    }

    private static class DetailUserRoleConverter extends Converter<DetailUserRoleDTO, UserRoleRef> {

        @Override
        protected UserRoleRef doForward(DetailUserRoleDTO dto) {
            return null;
        }

        @Override
        protected DetailUserRoleDTO doBackward(UserRoleRef ref) {
            DetailUserRoleDTO dto = new DetailUserRoleDTO();
            dto.setRoleId(ref.getRole().getRoleId());
            dto.setRoleCode(ref.getRole().getRoleCode());
            dto.setRoleName(ref.getRole().getRoleName());
            dto.setUserId(ref.getUser().getUserId());
            dto.setUserRoleId(ref.getUserRoleId());
            return dto;
        }
    }
}
