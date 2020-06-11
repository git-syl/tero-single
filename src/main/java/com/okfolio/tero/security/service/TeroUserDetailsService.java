package com.okfolio.tero.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/11
 */
public class TeroUserDetailsService implements ITeroUserDetailsService {

    @Override
    public UserDetails loadUserByPhone(String phone) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
