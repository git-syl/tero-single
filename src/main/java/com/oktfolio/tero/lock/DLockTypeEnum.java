package com.oktfolio.tero.lock;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public enum DLockTypeEnum {
    /**
     * 非公平锁
     */
    NON_FAIR,
    /**
     * 公平锁
     */
    FAIR,
    /**
     * 读写锁
     */
    READ_WRITE
}
