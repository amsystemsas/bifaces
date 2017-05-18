package com.amsystem.bifaces.util;

/**
 * Title: NodeType.java
 * @author jaguilar (JAR)
 * File Creation on 07/05/2016
 */
public enum PageMode {

    CREATE (0, "Create"),
    EDIT (1, "Edit"),
    CONSULT(2,"Consult");

    private int value;
    private String label;

    private PageMode(int value, String label) {
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


    public static PageMode valueOf(int value) {
        for (PageMode ct : values()) {
            if (ct.getValue() == value) {
                return ct;
            }
        }
        return null;
    }

}
