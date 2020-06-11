package com.okfolio.tero.security.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/11
 */
public class TeroUserDetails implements ITeroUserDetails {

    private String username;
    private String phone;
    private String email;
    private String password;
    private Boolean credentialExpired;
    private Boolean locked;
    private Boolean enabled;
    private Boolean expired;
    private Boolean deleted;
    private Set<String> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredentialExpired(Boolean credentialExpired) {
        this.credentialExpired = credentialExpired;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public TeroUserDetails() {
    }

    public TeroUserDetails(String username,
                           String phone,
                           String email,
                           String password,
                           Boolean credentialExpired,
                           Boolean locked,
                           Boolean enabled,
                           Boolean expired,
                           Boolean deleted,
                           Set<String> authorities) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.credentialExpired = credentialExpired;
        this.locked = locked;
        this.enabled = enabled;
        this.expired = expired;
        this.deleted = deleted;
        this.authorities = authorities;
    }
}
