package com.jianke.demo.exception;

import lombok.Data;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 21:47
 **/
@Data
public class BaseException extends RuntimeException {

    private String code;

    private String message;

    public BaseException(String message) {
        this.message = message;
    }

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
