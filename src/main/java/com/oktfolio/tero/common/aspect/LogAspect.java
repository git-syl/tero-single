package com.oktfolio.tero.common.aspect;

import com.oktfolio.tero.common.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
@Aspect
public class LogAspect {

    @Pointcut("@annotation(com.oktfolio.tero.common.annotation.Log)")
    public void controllerAspect() {
    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint, Log log, Authentication authentication) {


    }

    @After("controllerAspect()")
    public void after(JoinPoint joinPoint, Authentication authentication) {
    }
}
