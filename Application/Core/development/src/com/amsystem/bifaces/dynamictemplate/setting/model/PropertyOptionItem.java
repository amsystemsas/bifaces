package com.amsystem.bifaces.dynamictemplate.setting.model;

import java.util.Set;

/**
 * Clase que representa un Item de una propiedad cuyo comportamiento este representado por un componente de tipo
 * <tt>List</tt>, <tt>Check Box List</tt> etc.
 *
 * Title: PropertyOptionItem.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 27/07/2016.
 */
public class PropertyOptionItem {
    private Integer poiId;
    private Integer propertyId;
    private float value;
    private String description;
    private Set<PropertyOptionItemLabel> itemLabels;


    public PropertyOptionItem(Integer propertyId, float value, String description, Set<PropertyOptionItemLabel> itemLabels) {
        this.propertyId = propertyId;
        this.value = value;
        this.description = description;
        this.itemLabels = itemLabels;
    }

    public PropertyOptionItem(Integer poiId, Integer propertyId, float value, String description, Set<PropertyOptionItemLabel> itemLabels) {
        this.poiId = poiId;
        this.propertyId = propertyId;
        this.value = value;
        this.description = description;
        this.itemLabels = itemLabels;
    }

    public Integer getPoiId() {
        return poiId;
    }

    public void setPoiId(Integer poiId) {
        this.poiId = poiId;
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

    public Set<PropertyOptionItemLabel> getItemLabels() {
        return itemLabels;
    }

    public void setItemLabels(Set<PropertyOptionItemLabel> itemLabels) {
        this.itemLabels = itemLabels;
    }
}
