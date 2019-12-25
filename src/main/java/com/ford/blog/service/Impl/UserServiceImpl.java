package com.ford.blog.service.Impl;

import com.ford.blog.entity.User;
import com.ford.blog.entity.role.UserRoleRef;
import com.ford.blog.repository.UserRepository;
import com.ford.blog.service.PermissionService;
import com.ford.blog.service.UserService;
import com.ford.blog.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 5:53
 **/
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private PermissionService permissionService;

    @Resource
    private UserRepository userRepository;

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        return userRepository.findByUserNameAndPasswordAndIsDeleteFalseAndIsUseTrue(username, password);
    }

    @Override
    public User queryByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User queryById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public Page<User> queryUsers(String realName, String username, String roleId, Pageable pageable) {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (StringUtils.isNotEmpty(realName)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("realName").as(String.class), "%" + realName + "%"));
            }
            if (StringUtils.isNotEmpty(username)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("username").as(String.class), "%" + username + "%"));
            }
            if (StringUtils.isNotEmpty(roleId)) {

                Path<UserRoleRef> userRolePath = root.join("userRoleRef", JoinType.LEFT);
                Subquery<String> subQuery = criteriaQuery.subquery(String.class);
                Root<UserRoleRef> userRoleRoot = subQuery.from(UserRoleRef.class);
                subQuery.select(userRoleRoot.get("userRoleId"));
                Predicate subPredicate = criteriaBuilder.conjunction();
                subPredicate = criteriaBuilder.and(subPredicate, criteriaBuilder.equal(userRoleRoot.get("role").get("roleId")
                        .as(String.class), roleId));
                subQuery.where(subPredicate);
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.in(userRolePath.get("userRoleId")).value(subQuery));

            }
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("isDelete").as(Boolean.class), false));
            criteriaQuery.distinct(true).where(predicate);
            return criteriaQuery.getRestriction();
        };

        return userRepository.findAll(spec, pageable);
    }

    @Override
    public Boolean isUsernameDuplicate(String username) {
        User user = userRepository.findByUserName(username);
        return null != user;
    }

    @Override
    public Boolean isPhoneDuplicate(String phone) {
        User user = userRepository.findByMobilePhone(phone);
        return null != user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User dbUser, User inputUser) {
        dbUser.setRealName(inputUser.getRealName());
        dbUser.setEmail(inputUser.getEmail());
        dbUser.setGender(inputUser.getGender());
        dbUser.setIsUse(inputUser.getIsUse());
        dbUser.setMobilePhone(inputUser.getMobilePhone());
        dbUser.setUserName(inputUser.getUserName());
        // 处理用户角色关系
        List<UserRoleRef> refs = dbUser.getUserRoleRef();
        refs.clear();
        if (!CollectionUtils.isEmpty(inputUser.getUserRoleRef())) {
            refs.addAll(inputUser.getUserRoleRef());
        }
        userRepository.save(dbUser);
    }

    @Override
    public Boolean isUsernameDuplicate(String username, String userId) {
        User user = userRepository.findByUserNameAndUserIdNot(username, userId);
        return null != user;
    }

    @Override
    public Boolean isPhoneDuplicate(String phone, String userId) {
        User user = userRepository.findByMobilePhoneAndUserIdNot(phone, userId);
        return null != user;
    }
    @Override
    public User getValidUser(String userId) {
        return userRepository.findByUserIdAndIsDeleteFalseAndIsUseTrue(userId);
    }

    @Override
    public void delete(User user) {
        user.setIsDelete(true);
        userRepository.save(user);
    }

    @Override
    public Boolean getCanEdit(User targetUser) {
        User currentUser = this.queryById(UserUtils.getUserId());
        // 当前登录用户是超级管理员，可以修改任何信息，但是不能修改自己的角色和启用状态
        if (currentUser.isRoot()) {
            return true;
        }

        // 当前登录用户是管理员
        if (currentUser.isAdmin()) {
            if (targetUser.isAdmin() || targetUser.isRoot()) {
                return false;
            }
            return true;
        }
        //允许拥有USER_UPDATE权限的人可以添加用户操作
        Boolean isAdmin = permissionService.isPermission(currentUser, "USER_UPDATE");
        if (isAdmin){
            return true;
        }
        // 当前登录用户是其他角色，都无法修改
        return false;
    }
}
