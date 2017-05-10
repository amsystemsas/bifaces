package com.amsystem.bifaces.dynamictemplate.util;

/**
 * Title: RenderingType.java
 * @author jaguilar (JAR)
 * File Creation on 03/04/2016
 */
public enum RenderingType {

    INPUT_TEXT(0, "Texto"),

    TEXTAREA(1, "Area Texto"),

    OPTION_LIST(2, "Lista"),

    RADIO_BUTTON(3, "Radio"),

    CHECKBOX_LIST(4, "Check List"),

    CHECKBOX(5, "Check"),

    DATE(6, "Fecha"),

    LABEL(7, "Etiqueta");

    private final int value;
    private final String label;


    private RenderingType(int value, String label) {
        this.value = value;
        this.label = label;

    }

    /**
     * Gets the value of property type
     * @return int
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the label of property type
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * Value of property type
     * @param value The value
     * @return PropertyType
     */
    public static RenderingType valueOf(int value) {
        for (RenderingType edt : values()) {
            if (edt.getValue() == value) {
                return edt;
            }
        }
        return null;
    }

}
