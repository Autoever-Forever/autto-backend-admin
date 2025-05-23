package com.example.auttobackendadmin.exception.ProductRegisteration;

import com.example.auttobackendadmin.exception.ProductException;

public class AlreadyExistProductException extends ProductException {

    private static final String message = "이미 생성한 공연 정보입니다.";
    private static final String errorCode = "P002";
    private static final Integer status = 400;

    public AlreadyExistProductException(String message, Integer status, String errorCode) {
        super(message, status, errorCode);
    }
}
