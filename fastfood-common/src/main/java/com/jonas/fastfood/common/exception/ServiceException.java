package com.jonas.fastfood.common.exception;

import java.io.Serializable;

/**
 * 业务异常
 */
public class ServiceException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public ServiceException() {
        super("业务异常");
    }

    public ServiceException(String msg) {
        super(msg);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
