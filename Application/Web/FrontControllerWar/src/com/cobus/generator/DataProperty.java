package com.cobus.generator;

import com.cobus.dynamictemplate.setting.model.Property;

/**
 * Title: DataProperty.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 09/08/2016.
 */
public class DataProperty {
    Property property;
    String value;

    public DataProperty() {
    }

    public DataProperty(Property property) {
        this.property = property;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
