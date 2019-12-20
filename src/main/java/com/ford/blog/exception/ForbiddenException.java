package com.ford.blog.exception;


import com.ford.blog.util.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName: ForbiddenException
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 5:32
 * * 表示没有权限访问接口。
 * * status:403
 **/
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends BaseException {
    public ForbiddenException() {
    }

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
