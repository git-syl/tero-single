package com.oktfolio.tero.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
@Configuration
public class ValidateConfig {


    @Bean
    public Validator validator() {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                // 开启快速校验--默认校验所有参数，false校验全部
                .addProperty("hibernate.validator.fail_fast", "false")
                .buildValidatorFactory().getValidator();
    }
}
