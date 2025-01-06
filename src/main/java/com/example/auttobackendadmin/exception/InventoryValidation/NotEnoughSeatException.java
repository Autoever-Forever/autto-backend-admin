package com.example.auttobackendadmin.exception.InventoryValidation;

import com.example.auttobackendadmin.common.exception.ApplicationException;

public class NotEnoughSeatException extends ApplicationException {
    private static final Integer STATUS = 404;
    private static final String ERROR_CODE = "SEAT-002";
    private static final String MESSAGE = "좌석 재고가 부족합니다.";

    public NotEnoughSeatException() {
        super(MESSAGE, STATUS, ERROR_CODE);
    }
}