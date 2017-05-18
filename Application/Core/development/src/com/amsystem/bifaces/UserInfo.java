package com.amsystem.bifaces;

import java.util.Locale;

/**
 * Title: UserInfo.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 18/10/2016.
 */
public class UserInfo {
    private String login;
    private Locale locale;
    private static UserInfo userInfo;


    public static UserInfo getUserInfo() {
        return userInfo;
    }

    private UserInfo(String login, Locale locale) {
        this.login = login;
        this.locale = locale;
    }

    public static synchronized void initUserDefault() {
        if (userInfo == null) {
            userInfo = new UserInfo("", new Locale("es"));
        }

    }

    @Override
    public UserInfo clone() {
        try {
            throw new CloneNotSupportedException();
        } catch (CloneNotSupportedException ex) {
            System.out.println("No se puede clonar un objeto de la clase UserInfo");
        }
        return null;
    }

    public static Locale getLocaleUser() {
        return userInfo.getLocale();
    }

    public static void setLocaleUser(Locale locale) {
        userInfo.setLocale(locale);
    }

    public static String getLoginUser() {
        return userInfo.getLogin();
    }

    public static void setLoginUser(String login) {
        userInfo.setLogin(login);
    }

    private String getLogin() {
        return login;
    }

    private void setLogin(String login) {
        this.login = login;
    }

    private Locale getLocale() {
        return locale;
    }

    private void setLocale(Locale locale) {
        this.locale = locale;
    }



}
