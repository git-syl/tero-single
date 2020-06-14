package com.oktfolio.tero.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
@Component
@ConfigurationProperties(prefix = "tero.security", ignoreInvalidFields = false, ignoreUnknownFields = true)
public class TeroSecurityConfig {
    private List<String> ignoreUris;

    public List<String> getIgnoreUris() {
        return ignoreUris;
    }

    public void setIgnoreUris(List<String> ignoreUris) {
        this.ignoreUris = ignoreUris;
    }
}