package com.oktfolio.tero.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/13
 */
public class InvalidVerificationCodeException extends AuthenticationException {
    public InvalidVerificationCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidVerificationCodeException(String msg) {
        super(msg);
    }
}
