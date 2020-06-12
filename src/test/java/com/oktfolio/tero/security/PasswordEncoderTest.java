package com.oktfolio.tero.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encode() {
        String password = "oktfolio";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println(encodedPassword);
    }
}
