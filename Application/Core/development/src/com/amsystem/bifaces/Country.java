package com.amsystem.bifaces;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: Country.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/03/2017.
 */

public class Country {
    private String country;
    private String defaultLanguage;
    private String ISO_code;
    private Map<String, Object> languageCountry = new HashMap<>();
    private static Country instanceCountry;

    private Country(String country, String defaultLanguage, String ISO_code, Map<String, Object> languageCountry) {
        this.country = country;
        this.defaultLanguage = defaultLanguage;
        this.ISO_code = ISO_code;
        this.languageCountry = languageCountry;
    }


    public static synchronized Country initCountryDefault(String country, String defaultLanguage, String ISO_code, Map<String, Object> languageCountry) {
        if (instanceCountry == null) {
            instanceCountry = new Country(country, defaultLanguage, ISO_code, languageCountry);
        }
        return instanceCountry;
    }

    public static String getCountryLocale() {
        return instanceCountry.getCountry();
    }

    public static String getDefaultISOCodeLanguage() {
        return instanceCountry.getISO_code();
    }

    public static Map<String, Object> getLanguage() {
        return instanceCountry.getLanguageCountry();
    }

    private String getCountry() {
        return country;
    }

    private void setCountry(String country) {
        this.country = country;
    }

    private Map<String, Object> getLanguageCountry() {
        return languageCountry;
    }

    private void setLanguageCountry(Map<String, Object> languageCountry) {
        this.languageCountry = languageCountry;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getISO_code() {
        return ISO_code;
    }

    public void setISO_code(String ISO_code) {
        this.ISO_code = ISO_code;
    }
}
