package com.oktfolio.tero.modules.verification.code.controller;

import com.oktfolio.tero.modules.sms.service.SmsService;
import com.oktfolio.tero.security.model.EmailCode;
import com.oktfolio.tero.security.model.PhoneCode;
import com.oktfolio.tero.security.userdetails.ITeroUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
@RestController
public class VerificationCodeController {

    private final StringRedisTemplate stringRedisTemplate;

    private final ITeroUserDetailsService userDetailsService;

    private final SmsService smsService;

    public VerificationCodeController(StringRedisTemplate stringRedisTemplate, ITeroUserDetailsService userDetailsService, SmsService smsService) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.userDetailsService = userDetailsService;
        this.smsService = smsService;
    }

    @PostMapping("/verification-code/captcha")
    public ResponseEntity<Object> imageVerification(HttpSession httpSession) {
        var sessionId = httpSession.getId();
        var code = randomStringCode();
        stringRedisTemplate.opsForValue().set(Verification.Code.CAPTCHA + sessionId,
                code,
                15,
                TimeUnit.MINUTES);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verification-code/call")
    public ResponseEntity<Object> sendCallVerification(@RequestBody PhoneCode phoneCode) {
        var phone = phoneCode.getPhone();
        var userDetails = userDetailsService.loadUserByPhone(phone);
        var code = randomStringCode();
        if (userDetails != null) {
            stringRedisTemplate.opsForValue().set(Verification.Code.PHONE + phone,
                    code,
                    15,
                    TimeUnit.MINUTES);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verification-code/sms")
    public ResponseEntity<Object> sendSmsVerification(@RequestBody PhoneCode phoneCode) {
        var phone = phoneCode.getPhone();
        var userDetails = userDetailsService.loadUserByPhone(phone);
        var code = randomStringCode();
        if (userDetails != null) {
            stringRedisTemplate.opsForValue().set(Verification.Code.PHONE + phone,
                    code,
                    15,
                    TimeUnit.MINUTES);
        }
        smsService.produce(phone, code);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verification-code/email")
    public ResponseEntity<Object> sendEmailVerification(@RequestBody EmailCode emailCode) {
        var email = emailCode.getEmail();
        var userDetails = userDetailsService.loadUserByEmail(email);
        var code = randomStringCode();
        if (userDetails != null) {
            stringRedisTemplate.opsForValue()
                    .set(Verification.Code.EMAIL + email,
                            code,
                            15,
                            TimeUnit.MINUTES);
        }
        return ResponseEntity.ok().build();
    }

    private String randomStringCode() {
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }
}
