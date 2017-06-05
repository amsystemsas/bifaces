package com.amsystem.bifaces.dynamictemplate.setting.model;

import java.util.List;

/**
 * Title: Property.java
 *
 * @author Jaime Aguilar (JAR)
 * File Creation on 03/04/2016
 */
public class Property implements IFProperty{
    private Integer propertyId;
    private String name;
    private String label;
    private int type;
    private int renderingType;
    private String expressionValidator;
    private String formula;
    private String defaultValue;
    private boolean visible;
    private boolean editable;
    private boolean required;
    private String parent;
    private String mask;
    private List<PropertyOptionItem> propertyOptionItems;
    private List<Template> templateAssociated;


    public Property() { }

    public Property(Integer propertyId, String name){
        this.propertyId = propertyId;
        this.name = name;
    }

    public Property(Integer propertyId, String name, String label, int type, int renderingType,
                    String expressionValidator, String formula, String defaultValue,
                    boolean visible, boolean editable, boolean required, String parent, String mask) {

        this.propertyId = propertyId;
        this.name = name;
        this.label = label;
        this.type = type;
        this.renderingType = renderingType;
        this.expressionValidator = expressionValidator;
        this.formula = formula;
        this.defaultValue = defaultValue;
        this.visible = visible;
        this.editable = editable;
        this.required = required;
        this.parent = parent;
        this.mask = mask;

    }

    @Override
    public Integer getPropertyId() {
        return propertyId;
    }

    @Override
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getRenderingType() {
        return renderingType;
    }

    @Override
    public void setRenderingType(int renderingType) {
        this.renderingType = renderingType;
    }

    @Override
    public String getExpressionValidator() {
        return expressionValidator;
    }

    @Override
    public void setExpressionValidator(String expressionValidator) {
        this.expressionValidator = expressionValidator;
    }

    @Override
    public String getFormula() {
        return formula;
    }

    @Override
    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isEditable() {
        return editable;
    }

    @Override
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String getParent() {
        return parent;
    }

    @Override
    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String getMask() {
        return mask;
    }

    @Override
    public void setMask(String mask) {
        this.mask = mask;
    }

    @Override
    public List<PropertyOptionItem> getPropertyOptionItems() {
        return propertyOptionItems;
    }

    @Override
    public void setPropertyOptionItems(List<PropertyOptionItem> propertyOptionItems) {
        this.propertyOptionItems = propertyOptionItems;
    }
}
