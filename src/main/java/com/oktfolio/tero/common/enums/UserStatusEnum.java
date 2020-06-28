package com.oktfolio.tero.common.enums;

import java.util.LinkedHashMap;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/28
 */
public enum UserStatusEnum {
    /**
     * 密码过期
     */
    CREDENTIAL_EXPIRED(4, "密码过期"),
    /**
     * 已锁定
     */
    LOCKED(3, "已锁定"),
    /**
     * 已过期
     */
    EXPIRED(2, "已过期"),
    /**
     * 已启用
     */
    ENABLED(1, "已启用"),
    /**
     * 未启用
     */
    DISABLED(0, "未启用"),
    ;

    private final Integer value;
    private final String desc;


    UserStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return value;
    }

    public String desc() {
        return desc;
    }

    private static final LinkedHashMap<String, UserStatusEnum> MAP;

    static {
        MAP = new LinkedHashMap<>();
        for (UserStatusEnum e : values()) {
            MAP.put(String.valueOf(e.value), e);
        }
    }

    public static UserStatusEnum fromValue(int value) {
        return MAP.get(String.valueOf(value));
    }
}
