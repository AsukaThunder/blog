package com.ford.blog.controller.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: UpdateUserPasswordDTO
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 6:00
 **/
@Getter
@Setter
public class UpdateUserPasswordDTO {

    private String password;

    private String repeatPassword;

    private String oldPassword;
}
