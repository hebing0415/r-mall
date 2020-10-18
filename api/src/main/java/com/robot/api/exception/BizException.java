package com.robot.api.exception;

import com.robot.api.response.ErrorType;

/**
 * @author robot
 * @date 2020/1/8 14:25
 */

public class BizException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public BizException(ErrorType resultCode) {
        super(resultCode.getErrorMsg());
        this.errorCode = resultCode.getErrorCode();
        this.errorMessage = resultCode.getErrorMsg();
    }

    public BizException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BizException(String errorCode, String errorMessage,Throwable cause) {
        super(errorMessage,cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
