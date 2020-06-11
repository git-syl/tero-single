package com.okfolio.tero.security.authentication;

import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
public class PhoneAuthenticationToken extends AbstractTeroAuthenticationToken {
    public PhoneAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
