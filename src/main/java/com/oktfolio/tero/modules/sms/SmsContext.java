package com.oktfolio.tero.modules.sms;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
@Component
public class SmsContext {
    private final Map<String, SmsStrategy> smsContextStrategyMap = new ConcurrentHashMap<>();

    public SmsContext(Map<String, SmsStrategy> strategyMap) {
        strategyMap.forEach(this.smsContextStrategyMap::put);
    }

    public void send() {
        SmsStrategy sms = smsContextStrategyMap.get("ALI");
        sms.sendSms();
    }
}
