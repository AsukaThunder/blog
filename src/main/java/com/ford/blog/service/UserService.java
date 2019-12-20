package com.ford.blog.service;

import com.ford.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @ClassName: UserService
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 5:51
 **/
public interface UserService {

    /**
     * 根据用户名密码查询
     *
     * @param username
     * @param password
     * @return
     */
    User queryByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    User queryByUsername(String  username);
    /**
     * 根据id查询
     *
     * @param userId
     * @return
     */

    User queryById(String userId);

    /**
     * 查询用户列表
     *
     * @param realName
     * @param pageable
     * @return
     */
    Page<User> queryUsers(String realName, String username, String roleId, Pageable pageable);

    /**
     * 用户名是否重复
     *
     * @param username
     * @return
     */
    Boolean isUsernameDuplicate(String username);

    /**
     * 用户名是否重复
     *
     * @param username
     * @param userId
     * @return
     */
    Boolean isUsernameDuplicate(String username, String userId);

    /**
     * 手机号是否重复
     *
     * @param phone
     * @param userId
     * @return
     */
    Boolean isPhoneDuplicate(String phone, String userId);

    /**
     * 判断userId是否合法
     *
     * @param userId
     * @return
     */
    User getValidUser(String userId);

    /**
     * 手机号是否重复
     *
     * @param phone
     * @return
     */
    Boolean isPhoneDuplicate(String phone);

    /**
     * 新建用户
     *
     * @param user
     */
    void save(User user);

    /**
     * 更新用户
     *
     * @param dbUser
     * @param inputUser
     */
    void update(User dbUser, User inputUser);

    /**
     * 判断当前用户能否编辑
     *
     * @param targetUser
     * @return
     */
    Boolean getCanEdit(User targetUser);

    /**
     * 删除用户
     * @param user
     */
    void delete(User user);
}

