package com.example.auttobackendadmin.exception.InventoryValidation;

import com.example.auttobackendadmin.common.exception.ApplicationException;

public class NotExistingSeatException extends ApplicationException {
    private static final Integer STATUS = 404;
    private static final String ERROR_CODE = "SEAT-001";
    private static final String MESSAGE = "존재하지 않는 좌석입니다.";

    public NotExistingSeatException() {
        super(MESSAGE, STATUS, ERROR_CODE);
    }
} 