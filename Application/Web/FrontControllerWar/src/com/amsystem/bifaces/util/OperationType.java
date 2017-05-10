package com.amsystem.bifaces.util;

/**
 * Title: OperationType.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 12/01/2017.
 */

public enum OperationType {
    CREATE(0, "C"),
    EDIT(1, "E"),
    DELETE(2, "D");

    private int value;
    private String label;

    private OperationType(int value, String label) {
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


    public static OperationType valueOf(int value) {
        for (OperationType ot : values()) {
            if (ot.getValue() == value) {
                return ot;
            }
        }
        return null;
    }
}
