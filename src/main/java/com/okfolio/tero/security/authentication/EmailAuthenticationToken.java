package com.okfolio.tero.security.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
public class EmailAuthenticationToken extends AbstractTeroAuthenticationToken {

    public EmailAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public EmailAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
