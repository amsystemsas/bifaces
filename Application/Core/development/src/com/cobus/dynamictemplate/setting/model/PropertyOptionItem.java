package com.cobus.dynamictemplate.setting.model;

/**
 * Title: PropertyOptionItem.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 27/07/2016.
 */
public class PropertyOptionItem {
    private Integer id;
    private Integer propertyId;
    private String description;
    private float value;

    public PropertyOptionItem() {}

    public PropertyOptionItem(Integer id, Integer propertyId, String description, float value) {
        this.id = id;
        this.propertyId = propertyId;
        this.description = description;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
