package com.ford.blog.service.Impl;

import com.ford.blog.entity.role.UserRoleRef;
import com.ford.blog.repository.UserRoleRefRepository;
import com.ford.blog.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: UserRoleImpl
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/20 下午 4:09
 **/
@Service
public class UserRoleImpl implements UserRoleService {
    @Resource
    UserRoleRefRepository userRoleRefRepository;

    @Override
    public List<UserRoleRef> getByUserId(String userId) {
        return userRoleRefRepository.getByUserId(userId);
    }
}
