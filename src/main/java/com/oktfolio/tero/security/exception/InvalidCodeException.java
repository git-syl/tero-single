package com.oktfolio.tero.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/13
 */
public class InvalidCodeException extends AuthenticationException {
    public InvalidCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidCodeException(String msg) {
        super(msg);
    }
}
