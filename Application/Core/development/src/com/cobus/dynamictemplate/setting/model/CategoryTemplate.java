package com.cobus.dynamictemplate.setting.model;

/**
 * Title: CategoryTemplate.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public enum CategoryTemplate {
    
    GENERIC(1, "label.generic");
    
    private int value;
    private String name;

    private CategoryTemplate(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static CategoryTemplate valueOf(int value) {
        for (CategoryTemplate ct : values()) {
            if (ct.getValue() == value) {
                return ct;
            }
        }
        return null;
    }
    
    
    

}
