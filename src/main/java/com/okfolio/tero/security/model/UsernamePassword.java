package com.okfolio.tero.security.model;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/11
 */
public class UsernamePassword {
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsernamePassword{" +
                "username='" + username + '\'' +
                ", password='" + null + '\'' +
                '}';
    }
}
