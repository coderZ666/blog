package com.zwx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author coderZWX
 * @date 2020-11-04 15:02
 */
@ResponseStatus(HttpStatus.NOT_FOUND)//注解对应资源找不到状态，到404
public class NotFoundException extends RuntimeException{

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
