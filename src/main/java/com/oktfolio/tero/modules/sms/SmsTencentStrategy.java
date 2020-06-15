package com.oktfolio.tero.modules.sms;

import org.springframework.stereotype.Component;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
@Component("TENCENT")
public class SmsTencentStrategy extends AbstractSmsStrategy{


    @Override
    protected void send(SmsDO smsDO) {

    }
}
