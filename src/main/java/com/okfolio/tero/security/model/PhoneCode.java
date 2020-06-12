package com.okfolio.tero.security.model;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
public class PhoneCode {
    /**
     * phone number
     */
    private String phone;
    /**
     * authentication code
     */
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PhoneCode() {
    }

    @Override
    public String toString() {
        return "PhoneCode{" +
                "phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
