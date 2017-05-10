package com.amsystem.bifaces.dynamictemplate.setting.model;


import java.util.Date;

/**
 * Title: DynamicObjectProperty.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public class PropertyTemplate {
    private Integer propertyId;
    private Integer templateId;
    private Date operationDate;

    public PropertyTemplate(Integer propertyId, Integer templateId) {
        this.propertyId = propertyId;
        this.templateId = templateId;
    }

    public PropertyTemplate(Integer propertyId, Integer templateId, Date operationDate) {
        this.propertyId = propertyId;
        this.templateId = templateId;
        this.operationDate = operationDate;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }
}
