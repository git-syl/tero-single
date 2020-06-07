package com.okfolio.tero.common.constant;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
public interface AuthorityCode {

    interface AdminUser {
        String CREATE = "ADMIN_USER_LIST_CREATE";
        String HAS_AUTHORITY_CREATE = "hasAuthority('" + CREATE + ")";
        String DELETE = "ADMIN_USER_LIST_DELETE";
        String HAS_AUTHORITY_DELETE = "hasAuthority('" + DELETE + "')";
        String UPDATE = "ADMIN_USER_LIST_UPDATE";
        String HAS_AUTHORITY_UPDATE = "hasAuthority('" + UPDATE + "')";
        String RETRIEVE = "ADMIN_USER_LIST_RETRIEVE";
        String HAS_AUTHORITY_RETRIEVE = "hasAuthority('" + RETRIEVE + "')";
    }
}
