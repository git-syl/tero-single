package com.oktfolio.tero.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
public interface ITeroUserDetails extends UserDetails {

    String getEmail();

    String getPhone();
}
