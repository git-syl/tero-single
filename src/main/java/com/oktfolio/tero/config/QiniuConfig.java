package com.oktfolio.tero.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
@Configuration
@ConfigurationProperties(prefix = "tero.qiniu", ignoreInvalidFields = false, ignoreUnknownFields = true)
public class QiniuConfig {

    private String accessKey;
    private String secretKey;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
