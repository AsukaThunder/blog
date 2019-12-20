package com.ford.blog.controller.user.dto;

import com.ford.blog.encrypt.util.CheckoutUtil;
import com.ford.blog.entity.User;
import com.ford.blog.entity.role.Role;
import com.ford.blog.entity.role.UserRoleRef;
import com.google.common.base.Converter;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SaveUserDTO
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 5:45
 **/
@Setter
@Getter
public class SaveUserDTO {

    private String userId;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "真实姓名不能为空")
    private String realName;

    @NotNull(message = "手机号不能为空")
    private String mobilePhone;

    /**
     *   是否启用
     */
    private Boolean isUse;

    private Integer gender;

    private String email;

    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 角色id
     */
    @NotNull(message = "角色不能为空")
    private List<String> roleIds;

    public User convertTo() {
        SaveUserConverter converter = new SaveUserConverter();
        return converter.convert(this);
    }

    public SaveUserDTO convertFrom(User user) {
        SaveUserConverter converter = new SaveUserConverter();
        return converter.reverse().convert(user);
    }


    private static class SaveUserConverter extends Converter<SaveUserDTO, User> {

        @Override
        protected User doForward(SaveUserDTO dto) {
            User user = new User();
            user.setRealName(dto.getRealName());
            user.setUserName(dto.getUsername());
            user.setMobilePhone(dto.getMobilePhone());
            user.setRealName(dto.getRealName());
            user.setEnabled(dto.getIsUse());
            user.setEmail(dto.getEmail());
            user.setGender(dto.getGender());
            user.setPassword(CheckoutUtil.md5(dto.getPassword()));

            // 设置权限
            List<String> roleIds = dto.getRoleIds();
            List<UserRoleRef> userRoleRefs = new ArrayList<>();
            for (String roleId : roleIds) {
                Role role = new Role();
                role.setRoleId(roleId);

                UserRoleRef ref = new UserRoleRef();
                ref.setRole(role);
                ref.setUser(user);
                userRoleRefs.add(ref);
            }
            user.setUserRoleRef(userRoleRefs);
            return user;
        }

        @Override
        protected SaveUserDTO doBackward(User user) {
            return null;
        }
    }
}
