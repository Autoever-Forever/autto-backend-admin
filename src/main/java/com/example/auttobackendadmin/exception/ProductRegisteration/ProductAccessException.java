package com.example.auttobackendadmin.exception.ProductRegisteration;

import com.example.auttobackendadmin.exception.ProductException;

public class ProductAccessException extends ProductException {

    private static final String message = "공연 정보 생성 및 변경에 대한 권한이 없습니다.";
    private static final String errorCode = "P003";
    private static final Integer status = 400;

    public ProductAccessException() {
      super(message, status, errorCode);
    }
}
