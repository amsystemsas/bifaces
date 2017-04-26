package com.cobus.dynamictemplate.setting.dao;

import com.cobus.dynamictemplate.setting.model.Property;

import java.util.List;

/**
 * Title: RenderingType.java
 *
 * @author jaguilar (JAR) 
 * File Creation on 03/04/2016
 */
public interface IPropertyDao {

    /**
     *
     * @param propertyName the value of propertyName
     */
    void save(String propertyName);

    /**
     *
     * @param propertyName the value of propertyName
     */
    void delete(String propertyName);

    /**
     * 
     * @param property 
     */
    void update(Property property);

    /**
     * 
     * @param propertyId
     * @return 
     */
    Property getPropertyById(Integer propertyId);

    /**
     * 
     * @return Property List
     */
    List<Property> getAllProperty();

    /**
     * 
     * @param propertyId
     * @return 
     */
    List<Property> getAllPropertyByIds(List propertyId);

}
