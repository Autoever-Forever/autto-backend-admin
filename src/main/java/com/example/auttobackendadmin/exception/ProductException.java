package com.example.auttobackendadmin.exception;

import com.example.auttobackendadmin.common.exception.ApplicationException;
import lombok.Getter;

@Getter
public abstract class ProductException extends ApplicationException {
    public ProductException(String message, Integer status, String errorCode) {
        super(message, status, errorCode);
    }
}