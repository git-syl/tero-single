package com.okfolio.tero.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/11
 */
public interface ITeroUserDetailsService extends UserDetailsService {

    UserDetails loadUserByPhone(String phone) throws UsernameNotFoundException;

    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
    
}
