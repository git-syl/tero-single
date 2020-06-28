package com.oktfolio.tero.common.enums;

import java.util.LinkedHashMap;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/28
 */
public enum RoleStatusEnum {

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


    RoleStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return value;
    }

    public String desc() {
        return desc;
    }

    private static final LinkedHashMap<String, RoleStatusEnum> MAP;

    static {
        MAP = new LinkedHashMap<>();
        for (RoleStatusEnum e : values()) {
            MAP.put(String.valueOf(e.value), e);
        }
    }

    public static RoleStatusEnum fromValue(int value) {
        return MAP.get(String.valueOf(value));
    }

}
