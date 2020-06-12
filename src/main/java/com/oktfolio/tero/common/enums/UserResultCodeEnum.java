package com.oktfolio.tero.common.enums;

import org.springframework.http.HttpStatus;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
public enum UserResultCodeEnum implements ResultCode {

    // failed to delete user roles
    FAILED_DELETE_USER_ROLES("400030", "failed to delete user roles", HttpStatus.OK),
    // failed to add user roles
    FAILED_ADD_USER_ROLES("400031", "failed to add user roles", HttpStatus.OK),
    // failed create user
    FAILED_CREATE_USER("400020", "failed to create user", HttpStatus.OK),
    // failed delete user
    FAILED_DELETE_USER("400021", "failed to delete user", HttpStatus.OK),
    // failed update user
    FAILED_UPDATE_USER("400022", "failed to update user", HttpStatus.OK),
    // failed update user password
    FAILED_UPDATE_USER_PASSWORD("400022", "failed to update user password", HttpStatus.OK),
    // username exists
    USERNAME_EXISTS("400010", "username already registered", HttpStatus.OK),
    // email exists
    EMAIL_EXISTS("400011", "email has been bound by other user", HttpStatus.OK),
    // phone exists
    PHONE_EXISTS("400012", "phone has been bound by other user", HttpStatus.OK),
    // username or password cannot be null
    USERNAME_PASSWORD_CANNOT_BE_NULL("401010", "username or password cannot be null", HttpStatus.OK),
    // bad username or password
    BAD_USERNAME_PASSWORD("401011", "bad username or password", HttpStatus.OK),
    // user not exist
    USER_NOT_EXIST("401012", "bad not exist", HttpStatus.OK),
    // invalid login status
    INVALID_LOGIN_STATUS("401113", "invalid login status, please re login", HttpStatus.OK),
    // invalid token
    INVALID_TOKEN("401014", "invalid token", HttpStatus.OK),
    // user expired
    USER_EXPIRED("401015", "uer expired", HttpStatus.OK),
    // user locked or disabled
    USER_LOCKED("401016", "user locked or disabled", HttpStatus.OK),
    // credentials expired
    CREDENTIALS_EXPIRED("401017", "credentials expired", HttpStatus.OK),
    // user disabled
    USER_DISABLED("401018", "user disabled", HttpStatus.OK),
    // failed login
    FAILED_LOGIN("401019", "failed login", HttpStatus.OK),
    // bad verification code
    INVALID_VERIFICATION_CODE("401020", "invalid verification code", HttpStatus.OK),
    // bad old password
    BAD_OLD_PASSWORD("401021", "bad old password", HttpStatus.OK),
    ;

    private final String value;
    private final String message;
    private final HttpStatus status;

    UserResultCodeEnum(String value, String message) {
        this.value = value;
        this.message = message;
        this.status = HttpStatus.OK;
    }

    UserResultCodeEnum(String value, String message, HttpStatus status) {
        this.value = value;
        this.message = message;
        this.status = status;
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
