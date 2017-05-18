package com.amsystem.bifaces.dynamictemplate.util;

/**
 * Title: PropertyType.java
 * @author jaguilar (JAR)
 * File Creation on 03/04/2016
 */
public enum PropertyType {
    /**
     * Indicates the property type as String
     */
    STRING(0),
    /**
     * Indicates the property type as Integer
     */
    INTEGER(1),
    /**
     * Indicates the property type as Float
     */
    FLOAT(2);

    private final int value;


    private PropertyType(int value) {
        this.value = value;

    }

    /**
     * Gets the value of property type
     * @return int
     */
    public int getValue() {
        return value;
    }

    /**
     * Value of property type
     * @param value The value
     * @return PropertyType
     */
    public static PropertyType valueOf(int value) {
        for (PropertyType edt : values()) {
            if (edt.getValue() == value) {
                return edt;
            }
        }
        return null;
    }

}
