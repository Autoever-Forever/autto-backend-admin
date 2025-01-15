package com.example.auttobackendadmin.exception.InventoryValidation;

import com.example.auttobackendadmin.common.exception.ApplicationException;

public class CancellationBelowZeroException extends ApplicationException {
    private static final Integer STATUS = 404;
    private static final String ERROR_CODE = "SEAT-003";
    private static final String MESSAGE = "취소 재고가 본 재고보다 많습니다.";

    public CancellationBelowZeroException() {
        super(MESSAGE, STATUS, ERROR_CODE);
    }
}