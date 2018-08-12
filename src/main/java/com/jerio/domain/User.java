package com.jerio.domain;

import java.io.Serializable;

/**
 * Created by Jerio on 2018/8/12
 */
public class User implements Serializable {

    private static final long serialVersionUID = 8849109943030950132L;

    private String username;
    private String password;

    public User(String username) {
        this.username = username;
    }

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
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
