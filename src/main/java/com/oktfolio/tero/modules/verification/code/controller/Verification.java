package com.oktfolio.tero.modules.verification.code.controller;

public interface Verification {
    interface Code {
        String PHONE = "verification:code:phone:";
        String EMAIL = "verification:code:email:";
        String CAPTCHA = "verification:code:image:";
    }
}