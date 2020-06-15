package com.oktfolio.tero.modules.sms.service;

import com.oktfolio.tero.modules.sms.SmsContext;
import com.oktfolio.tero.modules.sms.SmsStrategy;
import com.oktfolio.tero.modules.sms.producer.SmsMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
@Service
public class SmsService {

    private final SmsMqProducer smsMqProducer;

    @Autowired
    private SmsContext smsContext;

    public SmsService(SmsMqProducer smsMqProducer) {
        this.smsMqProducer = smsMqProducer;
    }

    public void produce(String phoneNumber, String code) {
        smsMqProducer.produce();
    }

    public void sendSms(SmsStrategy smsStrategy){
        smsContext.send();
    }
}
