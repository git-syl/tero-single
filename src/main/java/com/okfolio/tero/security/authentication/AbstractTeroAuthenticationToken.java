package com.okfolio.tero.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
public abstract class AbstractTeroAuthenticationToken extends AbstractAuthenticationToken {
    public AbstractTeroAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }
}
