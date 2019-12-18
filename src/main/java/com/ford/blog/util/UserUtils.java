package com.ford.blog.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户工具类
 */
public class UserUtils {

    private final static String SESSION_NAME = "session_user";


    /**
     * 用户类型
     */
    public enum UserType {
        /**
         * 游客
         */
        visitor,
        /**
         * 博主
         */
        blogger,
        /**
         * 平台管理员
         */
        admin,
        /**
         * 什么都不是
         */
        none
    }

    /**
     * 获取当前请求
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取session 中的user
     *
     * @return
     */
    public static Object getUser() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }
        Object user = session.getAttribute(SESSION_NAME);
        return user;
    }

    /**
     * 获取用户类型
     *
     * @param user
     * @return
     */
//    public static UserType getUserType(Class<?> user) {
//        if (user.equals(HospitalUser.class)) {
//            return UserType.hospital;
//        } else if (user.equals(SupplierEmployee.class)) {
//            return UserType.supplier;
//        } else if (user.equals(Admin.class)) {
//            return UserType.admin;
//        } else if (user.equals(HospitalExternalUser.class)) {
//            return UserType.external;
//        } else {
//            return UserType.none;
//        }
//    }

//    /**
//     * 获取用户类型
//     *
//     * @return
//     */
//    public static UserType getUserType() {
//        Object user = getUser();
//        if (user == null) {
//            return UserType.none;
//        }
//        if (user.getClass().equals(HospitalUser.class)) {
//            return UserType.hospital;
//        } else if (user.getClass().equals(SupplierEmployee.class)) {
//            return UserType.supplier;
//        } else if (user.getClass().equals(Admin.class)) {
//            return UserType.admin;
//        } else if (user.getClass().equals(HospitalExternalUser.class)) {
//            return UserType.external;
//        } else {
//            return UserType.none;
//        }
//    }

//    /**
//     * 转换成医院员工
//     *
//     * @param user
//     * @return
//     */
//    public static HospitalUser getHospitalUser(Object user) {
//        HospitalUser hospitalUser = (HospitalUser) user;
//        return hospitalUser;
//    }
//
//    /**
//     * 转换成服务商员工
//     *
//     * @param user
//     * @return
//     */
//    public static SupplierEmployee getSupplierEmployee(Object user) {
//        SupplierEmployee employee = (SupplierEmployee) user;
//        return employee;
//    }
//
//    public static HospitalExternalUser getHospitalExternalUser(Object user) {
//        HospitalExternalUser hospitalExternalUser = (HospitalExternalUser) user;
//        return hospitalExternalUser;
//    }

    /**
     * 转换成平台管理员
     *
     * @param user
     * @return
     */
//    public static Admin getAdmin(Object user) {
//        Admin admin = (Admin) user;
//        return admin;
//    }

    /**
     * 获取用户ID
     *
     * @return
     */
//    public static String getUserId() {
//        Object user = getUser();
//        if (user == null) {
//            return null;
//        }
//        UserType type = getUserType(user.getClass());
//        if (UserType.hospital.equals(type)) {
//            HospitalUser hospitalUser = getHospitalUser(user);
//            return hospitalUser.getEmployeeId();
//        } else if (UserType.supplier.equals(type)) {
//            SupplierEmployee employee = getSupplierEmployee(user);
//            return employee.getUserId();
//        } else if (UserType.admin.equals(type)) {
//            Admin admin = getAdmin(user);
//            return admin.getAdminId();
//        } else if (UserType.external.equals(type)) {
//            HospitalExternalUser hospitalExternalUser = getHospitalExternalUser(user);
//            return hospitalExternalUser.getUserId();
//        } else {
//            return null;
//        }
//    }

    /**
     * 是否是博主
     *
     * @param userType 用户类型
     * @return boolean
     */
    public static boolean isBlogger(UserUtils.UserType userType) {
        if (UserType.blogger.equals(userType)) {
            return true;
        }
        return false;
    }

    /**
     * 是否是平台管理员
     *
     * @param userType
     * @return
     */
    public static boolean isAdmin(UserUtils.UserType userType) {
        if (UserType.admin.equals(userType)) {
            return true;
        }
        return false;
    }


//    /**
//     * 检查前端传来是否是当前用户ID
//     *
//     * @param userId
//     */
//    public static void checkUserId(String userId) {
//        if (StringUtils.isNotBlank(userId)) {
//            Object user = getUser();
//            UserType type = getUserType(user.getClass());
//            if (UserType.hospital.equals(type)) {
//                HospitalUser hospitalUser = getHospitalUser(user);
//                if (!userId.equals(hospitalUser.getEmployeeId())) {
//                    throw new ResourceNotFoundException(ErrorCode.UserNotFoundException);
//                }
//            } else if (UserType.supplier.equals(type)) {
//                SupplierEmployee employee = getSupplierEmployee(user);
//                if (!userId.equals(employee.getUserId())) {
//                    throw new ResourceNotFoundException(ErrorCode.UserNotFoundException);
//                }
//            } else if (UserType.admin.equals(type)) {
//                Admin admin = getAdmin(user);
//                if (!userId.equals(admin.getAdminId())) {
//                    throw new ResourceNotFoundException(ErrorCode.UserNotFoundException);
//                }
//            } else {
//                throw new ResourceNotFoundException(ErrorCode.UserNotFoundException);
//            }
//        }
//    }
}
