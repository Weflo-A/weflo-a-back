package com.weflo.backend.global.error.exception;

import com.weflo.backend.global.error.ErrorCode;

public class InternalServerException extends BusinessException {
    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
