package com.amsystem.bifaces.util;

/**
 * Title: CategoryName.java
 * @author jaguilar (JAR)
 * File Creation on 07/05/2016
 */
public enum CategoryName {
    
    PRODUCT(1, "Product", "PR"),
    POLICY(2, "Policy", "POL"),
    RISK_UNIT(3, "RiskUnit", "RU"),
    INSURANCE_OBJECT(4, "InsuranceObject", "IO"),
    COVERAGE(5, "Coverage", "COV"),
    PARTICIPATION(6, "Participation", "CL"),
    SUMMARY(7, "Summary", "SUM"),
    GENERIC (8, "Generic", "GEN");


    private int value;
    private String label;
    private String prefix;

    private CategoryName(int value, String label, String prefix) {
        this.value = value;
        this.label = label;
        this.prefix = prefix;
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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
