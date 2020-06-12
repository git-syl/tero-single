package com.oktfolio.tero.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
@Component
@ConfigurationProperties(prefix = "kite.snowflake", ignoreInvalidFields = false, ignoreUnknownFields = true)
public class TeroSnowFlakeConfig {

    private Long centerId;
    private Long machineId;

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
}
