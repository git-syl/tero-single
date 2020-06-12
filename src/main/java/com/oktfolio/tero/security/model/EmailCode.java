package com.oktfolio.tero.security.model;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
public class EmailCode {
    /**
     * email address
     */
    private String email;
    /**
     * authentication code
     */
    private String code;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EmailCode() {
    }

    @Override
    public String toString() {
        return "EmailCode{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
