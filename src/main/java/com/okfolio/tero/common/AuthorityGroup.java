package com.okfolio.tero.common;

import com.okfolio.tero.common.enums.AuthorityGroupEnum;

import java.util.List;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
public class AuthorityGroup {

    private AuthorityGroupEnum group;
    private List<Authority> authorities;

    public AuthorityGroup(AuthorityGroupEnum group, List<Authority> authorities) {
        this.group = group;
        this.authorities = authorities;
    }

    public AuthorityGroupEnum getGroup() {
        return group;
    }

    public void setGroup(AuthorityGroupEnum group) {
        this.group = group;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
