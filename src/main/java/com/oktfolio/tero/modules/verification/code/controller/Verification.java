package com.oktfolio.tero.modules.verification.code.controller;

public interface Verification {
    interface Code {
        String PHONE = "verification_code:phone:";
        String EMAIL = "verification_code:email:";
        String CAPTCHA = "verification_code:image:";
    }
}