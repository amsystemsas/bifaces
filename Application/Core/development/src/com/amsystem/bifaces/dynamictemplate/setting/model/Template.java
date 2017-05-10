package com.amsystem.bifaces.dynamictemplate.setting.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Title: DynamicObject.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public class Template implements Serializable{

    @Id
    @Column(name = "IDTR")
    private Integer templateId;

    @Column(name = "IDCT")
    private Integer categoryId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private Integer status;

    private List<IFProperty> propertyList;

    public Template() {
    }

    public Template(Integer idDynamicObject, String name) {
        this.templateId = idDynamicObject;
        this.name = name;
    }

    public Template(String name, Integer categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Template{" +
                "templateId=" + templateId +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public List<IFProperty> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<IFProperty> propertyList) {
        this.propertyList = propertyList;
    }
}
