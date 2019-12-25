package com.ford.blog.controller.user.dto;

import com.ford.blog.entity.User;
import com.google.common.base.Converter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: DetailUserDTO
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 4:20
 **/
@Setter
@Getter
public class DetailUserDTO {

    private String userId;

    private String username;

    private String realName;

    private String mobilePhone;

    // 是否启用
    private Boolean isUse;

    private Integer gender;

    private String email;

    private String token;

    private List<DetailUserRoleDTO> roles;
    /**
     * 返回给前端判断能否更改角色和停用启用状态
     */
    private Boolean canEdit;

    public User convertTo() {
        DetailUserConverter converter = new DetailUserConverter();
        return converter.convert(this);
    }

    public DetailUserDTO convertFrom(User user) {
        DetailUserConverter converter = new DetailUserConverter();
        return converter.reverse().convert(user);
    }


    public static class DetailUserConverter extends Converter<DetailUserDTO, User> {

        @Override
        protected User doForward(DetailUserDTO dto) {
            return null;
        }

        @Override
        protected DetailUserDTO doBackward(User user) {
            DetailUserDTO dto = new DetailUserDTO();
            dto.setUserId(user.getUserId());
            dto.setUsername(user.getUserName());
            dto.setMobilePhone(user.getMobilePhone());
            dto.setRealName(user.getRealName());
            dto.setIsUse(user.getIsUse());
            dto.setEmail(user.getEmail());
            dto.setGender(user.getGender());
            dto.setRoles(user.getUserRoleRef()
                    .stream()
                    .filter(ref -> ref.getRole().getEnable())
                    .map(ref -> new DetailUserRoleDTO().convertFrom(ref))
                    .collect(Collectors.toList()));
            return dto;
        }
    }
}
