package com.amsystem.bifaces.generator;

import com.amsystem.bifaces.dynamictemplate.setting.model.IFProperty;
import com.amsystem.bifaces.dynamictemplate.setting.model.Property;

/**
 * Title: DataProperty.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 09/08/2016.
 */
public class DataProperty {
    IFProperty property;
    String value;

    public DataProperty() {
    }

    public DataProperty(IFProperty property) {
        this.property = property;
    }

    public IFProperty getProperty() {
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
