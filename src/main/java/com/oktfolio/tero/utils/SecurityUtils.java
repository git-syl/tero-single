package com.oktfolio.tero.utils;

import com.oktfolio.tero.security.userdetails.ITeroUserDetails;
import com.oktfolio.tero.security.userdetails.TeroUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/17
 */
@Component
public class SecurityUtils {

    public ITeroUserDetails getCurrentUser() {
        Authentication authentication = getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof ITeroUserDetails) {
            return (TeroUserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public ITeroUserDetails getCurrentUser(HttpServletRequest request) {
        Authentication authentication = getAuthentication(request);
        if (authentication != null && authentication.getPrincipal() instanceof ITeroUserDetails) {
            return (TeroUserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        return securityContextImpl.getAuthentication();
    }
}
