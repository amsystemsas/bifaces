package com.amsystem.bifaces.dynamictemplate.setting.model;

import java.util.List;

/**
 * Title: IFDynamicTableMaintenance.java
 * @author jaguilar (JAR)
 * File Creation on 05/07/2016
 */
public interface IFDynamicTableMaintenance {
    
    public String getGenerateCreateQuery();
    
    public String getGenerateDropQuery();
    
    public String getGenerateAlterAddQuery(List<Property> propertyList);
    
    public String getGenerateAlterDropQuery(List<Property> propertyList);
    
    public String getCountRowQuery();

}
