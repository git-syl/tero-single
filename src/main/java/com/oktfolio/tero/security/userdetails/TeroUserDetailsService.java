package com.oktfolio.tero.security.userdetails;

import com.google.common.collect.Sets;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/11
 */
@Component
public class TeroUserDetailsService implements ITeroUserDetailsService {

    @Override
    public UserDetails loadUserByPhone(String phone) throws UsernameNotFoundException {
        TeroUserDetails teroUserDetails = new TeroUserDetails();
        teroUserDetails.setUsername("oktfolio");
        teroUserDetails.setPassword("$2a$10$CjJT6cNe23jd1.Fg9APQvuTfhG8IAbk3I.kEInlnWKAFJUYmzaAxC");
        teroUserDetails.setAuthorities(Sets.newHashSet());
        return teroUserDetails;
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        TeroUserDetails teroUserDetails = new TeroUserDetails();
        teroUserDetails.setUsername("oktfolio");
        teroUserDetails.setPassword("$2a$10$VS2G6zYNE9jsRU0WZiFFcO4wPIPJKeuUmLymMXVglWya786UjNWPG");
        teroUserDetails.setAuthorities(Sets.newHashSet());
        return teroUserDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TeroUserDetails teroUserDetails = new TeroUserDetails();
        teroUserDetails.setUsername("oktfolio");
        teroUserDetails.setPassword("$2a$10$VS2G6zYNE9jsRU0WZiFFcO4wPIPJKeuUmLymMXVglWya786UjNWPG");
        teroUserDetails.setAuthorities(Sets.newHashSet());
        return teroUserDetails;
    }
}
