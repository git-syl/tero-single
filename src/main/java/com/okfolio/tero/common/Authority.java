package com.okfolio.tero.common;

import com.okfolio.tero.common.enums.AuthorityGroupEnum;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
public class Authority {

    private String code;
    private String name;
    private AuthorityGroupEnum group;

    public Authority(String code, String name, AuthorityGroupEnum group) {
        this.code = code;
        this.name = name;
        this.group = group;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorityGroupEnum getGroup() {
        return group;
    }

    public void setGroup(AuthorityGroupEnum group) {
        this.group = group;
    }
}
