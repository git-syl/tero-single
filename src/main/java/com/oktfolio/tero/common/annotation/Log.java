package com.oktfolio.tero.common.annotation;

import com.oktfolio.tero.common.enums.LogTypeEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    @AliasFor("value")
    String name() default "";
    @AliasFor("name")
    String value() default "";
    LogTypeEnum type() default LogTypeEnum.DEFAULT;
}
