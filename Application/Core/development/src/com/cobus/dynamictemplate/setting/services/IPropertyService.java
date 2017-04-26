package com.cobus.dynamictemplate.setting.services;

import com.cobus.dynamictemplate.setting.model.Property;
import com.cobus.dynamictemplate.setting.model.PropertyOptionItem;

import java.util.List;

/**
 * Title: PropertyService.java
 * @author jaguilar (JAR)
 * File Creation on 09/04/2016
 */
public interface IPropertyService {
    
    public void addProperty(String property);

    public void deleteProperty( String property);

    public void updateProperty(Property selectedProp);

    public List<Property> loadAllProperty();

    void updatePropertyOptionItem(PropertyOptionItem optionItemOld, float oldValue);

    public Property loadPropertyById(Integer idProperty, boolean optimize);

    public List<Property> findAllPropertyByIds(List idsProperty);

    void deleteOptionItem(PropertyOptionItem optionItem);

    //TODO: Eliminar este metodo, usuar carga lazzy
    void addOptionItem (PropertyOptionItem optionItem);

    //TODO: Eliminar este metodo, usuar carga lazzy
    List<PropertyOptionItem> loadPropertyOptionItem(Integer idProperty);
}
