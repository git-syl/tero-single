package com.oktfolio.tero.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/17
 */
@Configuration
public class SessionRegistryConfig {

    @Bean
    public SessionRegistry sessionRegistry(FindByIndexNameSessionRepository<? extends Session> sessionRepository) {
        return new SpringSessionBackedSessionRegistry<>(sessionRepository);
    }

    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
