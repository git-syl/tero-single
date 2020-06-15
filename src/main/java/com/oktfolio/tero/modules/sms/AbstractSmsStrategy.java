package com.oktfolio.tero.modules.sms;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public abstract class AbstractSmsStrategy implements SmsStrategy {

    @Override
    public void sendSms() {
        send(new SmsDO());
    }

    protected abstract void send(SmsDO smsDO);
}
