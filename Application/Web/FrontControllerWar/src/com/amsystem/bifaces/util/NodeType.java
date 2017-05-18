package com.amsystem.bifaces.util;

/**
 * Title: NodeType.java
 * @author jaguilar (JAR)
 * File Creation on 07/05/2016
 */
public enum NodeType {
    
    ROOT (0, "ROOT"),
    TEMPLATE_NAME (1, "TEMP"),
    PROPERTY(2,"PROP");
    
    private int value;
    private String label;

    private NodeType(int value, String label) {
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


    public static NodeType valueOf(int value) {
        for (NodeType ct : values()) {
            if (ct.getValue() == value) {
                return ct;
            }
        }
        return null;
    }
    
    
    

}
