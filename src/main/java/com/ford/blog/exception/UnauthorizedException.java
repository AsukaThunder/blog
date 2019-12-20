package com.ford.blog.exception;


import com.ford.blog.util.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName: UnauthorizedException
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 5:35
 *  * methods:[*]
 *  * 表示用户没有登录（令牌、用户名、密码错误）。
 *  * status:401
 **/
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends BaseException {

    public UnauthorizedException() {
    }

    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
