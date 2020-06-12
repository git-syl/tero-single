package com.oktfolio.tero.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
public class MethodNotSupportedException extends AuthenticationException {

    public MethodNotSupportedException(String msg, Throwable t) {
        super(msg, t);
    }

    public MethodNotSupportedException(String msg) {
        super(msg);
    }
}
