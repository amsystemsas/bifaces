package com.amsystem.bifaces.util;

import com.amsystem.bifaces.user.model.User;

/**
 * Title: UserInf.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 29/12/2016.
 */

public class UserInf {
    String login;
    String id;
    String country;
    String language;
    User user;

    public UserInf() {
    }

    public UserInf(User user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
