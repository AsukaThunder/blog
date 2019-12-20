package com.ford.blog.exception;

import com.ford.blog.util.ErrorCode;

/**
 * @ClassName: BaseException
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 5:31
 * 异常基类
 **/
public abstract class BaseException extends RuntimeException {

    private ErrorCode errorCode;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
