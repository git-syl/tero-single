package com.oktfolio.kite.security.authentication

import org.springframework.security.core.GrantedAuthority


/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/05/04
 */
class PhoneAuthenticationToken : AbstractKiteAuthenticationToken {
    constructor(principal: Any, credentials: Any?) : super(principal, credentials)
    constructor(principal: Any, credentials: Any?, authorities: Collection<GrantedAuthority?>?)
            : super(principal, credentials, authorities)
}