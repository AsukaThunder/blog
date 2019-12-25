package com.ford.blog.repository;

import com.ford.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName: UserRepository
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 7:14
 **/
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    User findByUserNameAndPasswordAndIsDeleteFalseAndIsUseTrue(String username, String password);

    User findByUserNameAndUserIdNot(String username, String userId);

    User findByMobilePhone(String mobilePhone);;

    User findByMobilePhoneAndUserIdNot(String mobilePhone, String userId);

    User findByUserIdAndIsDeleteFalseAndIsUseTrue(String userId);

    List<User> findByIsDeleteFalse();

    User findByUserNameAndPassword(String userName,String passWord);

    User findByUserName(String userName);
}
