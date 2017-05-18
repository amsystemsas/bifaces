package com.amsystem.bifaces.util;

/**
 * Title: DolphinLocaleType.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 18/10/2016.
 */
public enum DolphinLocaleType {
    DEFAULT_VALUE("d"),
    ENGLISH("en"),
    SPANISH("es"),
    ITALIAN("it"),
    PORTUGUESE("pt");

    private String value;

    DolphinLocaleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}
