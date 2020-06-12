package com.oktfolio.tero.common.enums;

import org.springframework.http.HttpStatus;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
public interface ResultCode {
    /**
     * value
     *
     * @return
     */
    String value();

    /**
     * message
     *
     * @return
     */
    String message();

    /**
     * status
     *
     * @return
     */
    HttpStatus status();
}
