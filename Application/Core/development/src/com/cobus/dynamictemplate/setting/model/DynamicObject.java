package com.cobus.dynamictemplate.setting.model;

import java.io.Serializable;
import java.util.List;

/**
 * Title: DynamicObject.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public class DynamicObject implements Serializable{
    
    private Integer doId;
    private Integer category;
    private Integer status;
    private String name;
    private List<Property> propertyList;

    public DynamicObject() {
    }

    public DynamicObject(Integer idDynamicObject, String name) {
        this.doId = idDynamicObject;
        this.name = name;
    }

    public DynamicObject(String name) {
        this.name = name;
    }
    
    
    public Integer getDoId() {
        return doId;
    }

    public void setDoId(Integer doId) {
        this.doId = doId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }
}
