package com.cobus.generator;


import com.cobus.dynamictemplate.setting.model.DynamicObject;
import com.cobus.dynamictemplate.setting.model.Property;
import com.cobus.util.PageMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: DataTemplate.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 09/08/2016.
 */
public class DataTemplate {
    DynamicObject template;
    List<DataProperty> dataProperties;
    PageMode pageMode;

    public DataTemplate(DynamicObject template, PageMode pageMode) {
        this.template = template;
        this.pageMode = pageMode;
        init();
    }

    private void init() {
        dataProperties = new ArrayList<>();
        for (Property prop : template.getPropertyList()){
            dataProperties.add(new DataProperty(prop));
        }
    }

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }

    public DynamicObject getTemplate() {
        return template;
    }

    public void setTemplate(DynamicObject template) {
        this.template = template;
    }

    public List<DataProperty> getDataProperties() {
        return dataProperties;
    }

    public void setDataProperties(List<DataProperty> dataProperties) {
        this.dataProperties = dataProperties;
    }
}
