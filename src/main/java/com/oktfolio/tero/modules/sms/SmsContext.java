package com.oktfolio.tero.modules.sms;

import com.oktfolio.tero.modules.sms.service.SmsService;
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

    private final SmsService smsService;

    public SmsContext(Map<String, SmsStrategy> strategyMap,
                      SmsService smsService) {
        strategyMap.forEach(this.smsContextStrategyMap::put);
        this.smsService = smsService;
    }


    public void smsSend() {
        SmsStrategy aliSms = smsContextStrategyMap.get("ALI");
        aliSms.sendSms();
    }
}
