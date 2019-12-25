package com.ford.blog.controller.user;

import com.ford.blog.controller.user.dto.*;
import com.ford.blog.encrypt.anno.Encrypt;
import com.ford.blog.encrypt.util.CheckoutUtil;
import com.ford.blog.entity.User;
import com.ford.blog.exception.InvalidRequestException;
import com.ford.blog.exception.ResourceNotFoundException;
import com.ford.blog.exception.UnprocessableEntityException;
import com.ford.blog.service.UserRoleService;
import com.ford.blog.service.UserService;
import com.ford.blog.util.ErrorCode;
import com.ford.blog.util.UserUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: UserController
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 3:58
 **/
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;


    /**
     * 账号 姓名 角色
     *
     * @param realName
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/v1/users", produces = "application/json")
    @ApiOperation("查询用户列表")
    @Encrypt
    public Page<PageUserDTO> queryUsers(@RequestParam(value = "realName", required = false) String realName,
                                        @RequestParam(value = "username", required = false) String username,
                                        @RequestParam(value = "roleId", required = false) String roleId,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createTime");
        Page<User> users = userService.queryUsers(realName, username, roleId, pageRequest);
        return users.map(user -> {
            PageUserDTO result = new PageUserDTO().convertFrom(user);
            return result;
        });
    }

    /**
     * 1.超级管理员角色不能通过页面新增，修改，删除
     * 2.超级管理员可以修改除自己外所有人的角色和停用启用状态
     * 3.管理员可以修改非管理员用户的角色和停用启用状态
     * 4.管理员不可以修改自己的角色和停用启用状态
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/v1/users/{userId}", produces = "application/json")
    @ApiOperation("查询指定用户详情")
    @Encrypt
    public DetailUserDTO queryUserById(@PathVariable("userId") String userId) {
        User user = userService.queryById(userId);
        if (null == user) {
            throw new ResourceNotFoundException(ErrorCode.UserNotFoundException);
        }
        DetailUserDTO result = new DetailUserDTO().convertFrom(user);
        result.setCanEdit(userService.getCanEdit(user));
        return result;
    }

    @PostMapping(value = "/v1/users", produces = "application/json")
    @ApiOperation("新建用户")
    public void addUser(@Validated @RequestBody SaveUserDTO dto) {
        User user = dto.convertTo();

        // 校验用户名重复
        if (userService.isUsernameDuplicate(dto.getUsername())) {
            throw new UnprocessableEntityException(ErrorCode.UsernameDuplicate);
        }

        // 校验手机号重复
        if (userService.isPhoneDuplicate(dto.getMobilePhone())) {
            throw new UnprocessableEntityException(ErrorCode.MobilePhoneDuplicate);
        }
        userService.save(user);
    }

    @PutMapping(value = "/v1/users/{userId}", produces = "application/json")
    @ApiOperation("修改用户")
    public void update(@Validated @RequestBody UpdateUserDTO dto) {
        User dbUser = userService.queryById(dto.getUserId());
        if (null == dbUser) {
            throw new ResourceNotFoundException(ErrorCode.UserNotFoundException);
        }

        User inputUser = dto.convertTo();

        // 校验用户名重复
        if (dto.getUserId() != null && userService.isUsernameDuplicate(dto.getUsername(), dto.getUserId())) {
            throw new UnprocessableEntityException(ErrorCode.UsernameDuplicate);
        }

        // 校验手机号重复
        if (userService.isPhoneDuplicate(dto.getMobilePhone(), dto.getUserId())) {
            throw new UnprocessableEntityException(ErrorCode.MobilePhoneDuplicate);
        }

        userService.update(dbUser, inputUser);
    }

    @DeleteMapping(value = "/v1/users/{userId}", produces = "application/json")
    @ApiOperation("删除-软删除")
    public void delete(@PathVariable("userId") String userId) {
        User dbUser = userService.queryById(userId);
        if (null == dbUser) {
            throw new ResourceNotFoundException(ErrorCode.UserNotFoundException);
        }
        userService.delete(dbUser);
    }

    @ApiOperation(value = "修改个人密码")
    @PutMapping(value = "/v1/users/{userId}", params = "action=modifyPassword")
    public void modifyPwd(@PathVariable("userId") String userId, @RequestBody UpdateUserPasswordDTO dto) {
        String currentUserId = UserUtils.getUserId();
        if (!dto.getRepeatPassword().equals(dto.getPassword())) {
            throw new InvalidRequestException(ErrorCode.PasswordRepeatDifferent);
        }
        if (!userId.equals(currentUserId)) {
            throw new InvalidRequestException(ErrorCode.CannotModifyOtherPwd);
        }
        User user = userService.queryById(userId);
        if (null == user) {
            throw new ResourceNotFoundException(ErrorCode.UserNotFoundException);
        }

        if (!user.getPassword().equals(CheckoutUtil.md5(dto.getOldPassword()))) {
            throw new InvalidRequestException(ErrorCode.OldPasswordError);
        }
        user.setPassword(CheckoutUtil.md5(dto.getPassword()));
        userService.save(user);
    }

}
