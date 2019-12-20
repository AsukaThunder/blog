package com.ford.blog.exception;

import com.ford.blog.util.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName: ResourceNotFoundException
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 5:34
 *  * methods:[*]
 *  * 用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
 *  * status:404
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
