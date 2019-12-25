package com.ford.blog.controller.login.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: LoginDTO
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 3:26
 **/
@Getter
@Setter
public class LoginDTO {

    private String username;

    private String password;

    private Boolean fromMobile = false;
}
