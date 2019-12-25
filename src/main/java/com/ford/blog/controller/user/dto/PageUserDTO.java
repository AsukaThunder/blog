package com.ford.blog.controller.user.dto;

import com.ford.blog.entity.User;
import com.google.common.base.Converter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: PageUserDTO
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 4:05
 **/
@Setter
@Getter
public class PageUserDTO {
    private String userId;

    private String username;

    private String realName;

    private String mobilePhone;

    private Boolean isUse;

    private Integer gender;

    private List<String> roles;

    private String email;

    public User convertTo() {
        PageUserConverter converter = new PageUserConverter();
        return converter.convert(this);
    }

    public PageUserDTO convertFrom(User user) {
        PageUserConverter converter = new PageUserConverter();
        return converter.reverse().convert(user);
    }

    private static class PageUserConverter extends Converter<PageUserDTO, User> {

        @Override
        protected User doForward(PageUserDTO dto) {
            return null;
        }

        @Override
        protected PageUserDTO doBackward(User user) {
            PageUserDTO dto = new PageUserDTO();
            dto.setUserId(user.getUserId());
            dto.setUsername(user.getUserName());
            dto.setMobilePhone(user.getMobilePhone());
            dto.setRealName(user.getRealName());
            dto.setIsUse(user.getIsUse());
            dto.setGender(user.getGender());
            dto.setEmail(user.getEmail());
            dto.setRoles(user.getUserRoleRef()
                    .stream()
                    .filter(ref -> ref.getRole().getEnable())
                    .map(ref -> ref.getRole().getRoleName())
                    .collect(Collectors.toList()));
            return dto;
        }
    }
}
