package com.cobus.util;

/**
 * Title: CategoryName.java
 * @author jaguilar (JAR)
 * File Creation on 07/05/2016
 */
public enum CategoryName {
    
    ROOT (0, "Root"),
    GENERIC (1, "Generic"),
    PRODUCT(2, "Product"),
    POLICE(3, "Police"),
    RISK_UNIT(4, "RiskUnit"),
    INSURANCE_OBJECT(5, "InsuranceObject"),
    COVERAGE(5, "Coverage"),
    PARTICIPATION(6, "Participation"),
    SUMMARY(7, "Summary"),
    OTHER (3, "Other");

    private int value;
    private String label;

    private CategoryName(int value, String label) {
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

    

    public static CategoryName valueOf(int value) {
        for (CategoryName ct : values()) {
            if (ct.getValue() == value) {
                return ct;
            }
        }
        return null;
    }
    

}
