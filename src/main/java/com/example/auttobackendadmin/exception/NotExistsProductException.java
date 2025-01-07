package com.example.auttobackendadmin.exception;

public class NotExistsProductException extends ProductException {
    private static final String message = "존재하지 않는 공연 정보입니다.";
    private static final String errorCode = "P001";
    private static final Integer status = 400;

    public NotExistsProductException(String message, Integer status, String errorCode) {
        super(message, status, errorCode);
    }
}
