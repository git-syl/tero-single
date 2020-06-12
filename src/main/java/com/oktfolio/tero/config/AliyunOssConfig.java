package com.oktfolio.tero.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
@Configuration
@ConfigurationProperties(prefix = "tero.aliyun.oss", ignoreInvalidFields = false, ignoreUnknownFields = true)
public class AliyunOssConfig {

    private String endpoint;
    private String accessKeyId;
    private String secretAccessKey;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    @Bean
    public OSS oss() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);
    }
}
