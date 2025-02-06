package com.example.auttobackendadmin.common.exception;

import lombok.Getter;

@Getter
public abstract class ApplicationException extends RuntimeException {

    private Integer status;
    private String errorCode;

    public ApplicationException(String message, Integer status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
