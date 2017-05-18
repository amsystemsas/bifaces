package com.amsystem.bifaces.util;

/**
 * Title: ErrorType.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 05/01/2017.
 */

public enum NotificationType {

    INFO(0, "Info"),
    WARN(1, "Warn"),
    ERROR(2, "Error"),
    FATAL(3, "Fatal");

    private int value;
    private String label;

    private NotificationType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public static NotificationType valueOf(int value) {
        for (NotificationType et : values()) {
            if (et.getValue() == value) {
                return et;
            }
        }
        return null;
    }

}
