package com.oktfolio.tero.common.enums;

import org.springframework.http.HttpStatus;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
public enum ResultCodeEnum implements ResultCode {
    // Success
    SUCCESS("20000", "Success", HttpStatus.OK),
    // Invalid params
    INVALID_PARAMS("40010", "Invalid params", HttpStatus.BAD_REQUEST),
    // Unauthorized
    UNAUTHORIZED("40100", "Unauthorized", HttpStatus.UNAUTHORIZED),
    // Forbidden
    FORBIDDEN("40300", "Forbidden", HttpStatus.FORBIDDEN),
    // Access denied
    ACCESS_DENIED("40300", "Access denied", HttpStatus.FORBIDDEN),
    // Not found
    NOT_FOUND("40400", "Not found", HttpStatus.NOT_FOUND),
    // Internal server error
    INTERNAL_SERVER_ERROR("50000", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    // Error
    ERROR("50001", "Error", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final String value;
    private final String message;
    private final HttpStatus status;

    ResultCodeEnum(String value, String message, HttpStatus status) {
        this.value = value;
        this.status = status;
        this.message = message;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public String message() {
        return this.message;
    }

    @Override
    public HttpStatus status() {
        return this.status;
    }
}
