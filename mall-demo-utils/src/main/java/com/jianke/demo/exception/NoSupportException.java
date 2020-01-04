package com.jianke.demo.exception;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 22:03
 **/
public class NoSupportException extends BaseException {
    public NoSupportException() {
        super("000009", "服务方法不支持");
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
