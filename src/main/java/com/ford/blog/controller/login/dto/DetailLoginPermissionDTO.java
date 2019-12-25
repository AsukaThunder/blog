package com.ford.blog.controller.login.dto;

import com.ford.blog.entity.role.Permission;
import com.google.common.base.Converter;

import java.io.Serializable;

/**
 * @ClassName: DetailLoginPermissionDTO
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/24 下午 2:46
 **/
public class DetailLoginPermissionDTO  implements Serializable {
    private String permissionId;

    private String permissionCode;

    private String permissionName;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Permission convertTo() {
        DetailPermissionConverter detailPermissionConverter = new DetailPermissionConverter();
        return detailPermissionConverter.convert(this);
    }

    public DetailLoginPermissionDTO convertFrom(Permission permission) {
        DetailPermissionConverter detailPermissionConverter = new DetailPermissionConverter();
        return detailPermissionConverter.reverse().convert(permission);
    }

    private static class DetailPermissionConverter extends Converter<DetailLoginPermissionDTO, Permission> {

        @Override
        protected Permission doForward(DetailLoginPermissionDTO detailPermissionDTO) {
            return null;
        }

        @Override
        protected DetailLoginPermissionDTO doBackward(Permission permission) {
            DetailLoginPermissionDTO dto = new DetailLoginPermissionDTO();
            dto.setPermissionCode(permission.getPermissionCode());
            dto.setPermissionId(permission.getPermissionId());
            dto.setPermissionName(permission.getPermissionName());
            return dto;
        }
    }
}
