package com.oktfolio.tero.modules.admin.controller;

import com.oktfolio.tero.security.userdetails.TeroUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/17
 */
@RestController
public class AdminSessionController {

    private final SessionRegistry sessionRegistry;

    public AdminSessionController(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @GetMapping("/session")
    public void test() {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        for (var principal : principals) {
            TeroUserDetails userDetails = (TeroUserDetails) principal;
            System.out.println(userDetails.getUsername());
        }
    }

    @GetMapping("/test/session")
    public void test1(Authentication authentication) {


        sessionRegistry.getAllSessions(authentication.getPrincipal(), true)
                .forEach((e)-> sessionRegistry.removeSessionInformation(e.getSessionId()));
    }
}
