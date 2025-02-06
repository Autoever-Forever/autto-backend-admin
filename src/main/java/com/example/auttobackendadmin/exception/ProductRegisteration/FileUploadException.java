package com.example.auttobackendadmin.exception.ProductRegisteration;

import com.example.auttobackendadmin.exception.ProductException;

public class FileUploadException extends ProductException {
    private static final String message = "버킷 생성 실패.";
    private static final String errorCode = "P004";
    private static final Integer status = 400;

    public FileUploadException() {
        super(message, status, errorCode);
    }
}