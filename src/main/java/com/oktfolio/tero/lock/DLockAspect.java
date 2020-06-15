package com.oktfolio.tero.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
@Aspect
public class DLockAspect {

    @Around("@annotation(dLock) && execution(public * *(..))")
    public Object pointCut(ProceedingJoinPoint joinPoint, DLock dLock) {
        return null;
    }

}
