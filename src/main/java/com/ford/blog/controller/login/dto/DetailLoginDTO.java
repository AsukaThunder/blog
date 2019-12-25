package com.ford.blog.controller.login.dto;

import com.ford.blog.controller.user.dto.DetailUserRoleDTO;
import com.ford.blog.entity.User;
import com.ford.blog.entity.role.Permission;
import com.ford.blog.entity.role.RolePermissionRef;
import com.ford.blog.entity.role.UserRoleRef;
import com.google.common.base.Converter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    private static class DetailUserConverter extends Converter<DetailLoginDTO, User> {

        @Override
        protected User doForward(DetailLoginDTO dto) {
            return null;
        }

        @Override
        protected DetailLoginDTO doBackward(User user) {
            DetailLoginDTO dto = new DetailLoginDTO();
            dto.setUserId(user.getUserId());
            dto.setUsername(user.getUserName());
            dto.setMobilePhone(user.getMobilePhone());
            dto.setRealName(user.getRealName());
            dto.setIsUse(user.getIsUse());
            dto.setEmail(user.getEmail());
            dto.setGender(user.getGender());
            List<UserRoleRef> userRoleRefs = user.getUserRoleRef();
            List<RolePermissionRef> rolePermissionRefs = new ArrayList<>();
            if (!CollectionUtils.isEmpty(userRoleRefs)) {
                dto.setRoles(userRoleRefs.stream()
                        .filter(ref -> ref.getRole().getEnable())
                        .map(ref -> new DetailUserRoleDTO().convertFrom(ref)).collect(Collectors.toList()));

                for (UserRoleRef ref : userRoleRefs) {
                    rolePermissionRefs.addAll(ref.getRole().getRolePermissionRef());
                }
                List<Permission> permissions = rolePermissionRefs.stream()
                        .filter(rolePermissionRef -> rolePermissionRef.getRole().getEnable())
                        .map(RolePermissionRef::getPermission).collect(Collectors.toList());
                dto.setPermissions(permissions
                        .stream()
                        .filter(permission -> permission.getEnable())
                        .map(permission -> new DetailLoginPermissionDTO().convertFrom(permission))
                        .collect(Collectors.collectingAndThen(
                                Collectors.toCollection(
                                        () -> new TreeSet<>(Comparator.comparing(DetailLoginPermissionDTO::getPermissionCode))
                                ), ArrayList::new)));
            }

            dto.setIsAdmin(user.isAdmin() || user.isRoot());
            return dto;
        }
    }
}
