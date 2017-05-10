package com.amsystem.bifaces.dynamictemplate.util;

/**
 * Title: TemplateStatus.java
 * @author jaguilar (JAR)
 * File Creation on 07/05/2016
 */
public enum TemplateStatus {
    ACTIVE (1),
    INACTIVE (2),
    DELETED(3);
    
    private int value;

    private TemplateStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static TemplateStatus valueOf(int value) {
        for (TemplateStatus ct : values()) {
            if (ct.getValue() == value) {
                return ct;
            }
        }
        return null;
    }
    

}
