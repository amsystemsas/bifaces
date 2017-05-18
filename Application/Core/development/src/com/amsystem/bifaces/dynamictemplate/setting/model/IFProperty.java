package com.amsystem.bifaces.dynamictemplate.setting.model;

import java.io.Serializable;
import java.util.List;

/**
 * Title: IFProperty.java
 * @author Jaime Aguilar (JAR)
 * File Creation on 20/07/2016
 */
public interface IFProperty extends Serializable{
    
    public Integer getPropertyId();

    public void setPropertyId(Integer propertyId);

    public String getName();

    public void setName(String name);

    public String getLabel();

    public void setLabel(String label);

    public String getExpressionValidator();

    public void setExpressionValidator(String expressionValidator);

    public String getFormula();

    public void setFormula(String formula);

    public String getDefaultValue();

    public void setDefaultValue(String defaultValue);

    public boolean isVisible();

    public void setVisible(boolean visible);

    public boolean isEditable();

    public void setEditable(boolean required);

    public boolean isRequired();

    public void setRequired(boolean required);

    public String getParent();

    public void setParent(String parent);

    public int getType();

    public void setType(int propertyType);

    public int getRenderingType();

    public void setRenderingType(int renderingType);

    public String getMask();

    public void setMask(String mask);

    public List<PropertyOptionItem> getPropertyOptionItems();

    public void setPropertyOptionItems(List<PropertyOptionItem> propertyOptionItems);

}
