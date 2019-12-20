package com.ford.blog.exception;

import com.ford.blog.util.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName: UnprocessableEntityException
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 5:35
 *  * methods:[POST/PUT/PATCH]
 *  * 当创建一个对象时，发生一个验证错误。
 *  * status:422
 **/
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends BaseException {

    public UnprocessableEntityException() {
    }

    public UnprocessableEntityException(ErrorCode errorCode) {
        super(errorCode);
    }
}
