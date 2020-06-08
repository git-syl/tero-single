package com.okfolio.tero.common.enums;

import com.okfolio.tero.common.constant.AuthorityCode;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
public enum AuthorityEnum {

    /**
     *
     */
    ADMIN_USER_CREATE(
            AuthorityCode.Admin.User.CREATE,
            "管理_用户_列表_创建",
            AuthorityGroupEnum.ADMIN_USER),

    /**
     *
     */
    ADMIN_USER_DELETE(
            AuthorityCode.Admin.User.DELETE,
            "管理_用户_列表_删除",
            AuthorityGroupEnum.ADMIN_USER),

    /**
     *
     */
    ADMIN_USER_UPDATE(
            AuthorityCode.Admin.User.UPDATE,
            "管理_用户_列表_更新",
            AuthorityGroupEnum.ADMIN_USER),

    /**
     *
     */
    ADMIN_USER_RETRIEVE(
            AuthorityCode.Admin.User.RETRIEVE,
            "管理_用户_列表_查询",
            AuthorityGroupEnum.ADMIN_USER),
    ;

    private final String code;
    private final String desc;
    private final AuthorityGroupEnum group;

    AuthorityEnum(String code, String desc, AuthorityGroupEnum group) {
        this.code = code;
        this.desc = desc;
        this.group = group;
    }

    public String code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }

    public AuthorityGroupEnum group() {
        return this.group;
    }
}
