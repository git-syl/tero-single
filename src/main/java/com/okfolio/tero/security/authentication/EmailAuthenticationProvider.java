package com.okfolio.tero.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
public class EmailAuthenticationProvider extends AbstractEmailUserDetailsAuthenticationProvider{
    @Override
    protected void additionalAuthenticationChecks(UserDetails var1, EmailAuthenticationToken var2) throws AuthenticationException {

    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        return null;
    }

    @Override
    protected UserDetails retrieveUser(String var1, EmailAuthenticationToken var2) throws AuthenticationException {
        return null;
    }
}
