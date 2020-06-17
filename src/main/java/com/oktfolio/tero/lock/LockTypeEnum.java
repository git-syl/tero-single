package com.oktfolio.tero.lock;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/17
 */
public enum LockTypeEnum {
    /**
     * 联锁
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
