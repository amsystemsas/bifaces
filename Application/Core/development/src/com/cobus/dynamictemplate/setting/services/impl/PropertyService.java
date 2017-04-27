package com.cobus.dynamictemplate.setting.services.impl;

import com.cobus.dynamictemplate.setting.dao.IPropertyDao;
import com.cobus.dynamictemplate.setting.dao.PropertyOptionItemDao;
import com.cobus.dynamictemplate.setting.model.Property;
import com.cobus.dynamictemplate.setting.model.PropertyOptionItem;
import com.cobus.dynamictemplate.setting.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: PropertyBoImpl.java
 * @author jaguilar (JAR)
 * File Creation on 09/04/2016
 */
@Service("propertyService")
@Transactional
public class PropertyService implements IPropertyService {

    @Autowired
    private IPropertyDao propertyDao;

    @Autowired
    private PropertyOptionItemDao propertyOptionItemDao;


    public IPropertyDao getPropertyDao() {
        return propertyDao;
    }

    public void setPropertyDao(IPropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    public PropertyOptionItemDao getPropertyOptionItem() {
        return propertyOptionItemDao;
    }

    public void setPropertyOptionItem(PropertyOptionItemDao propertyOptionItemDao) {
        this.propertyOptionItemDao = propertyOptionItemDao;
    }


    @Override
    public void addProperty(String property) {
        propertyDao.save(property);
        
    }

    @Override
    public void deleteProperty(java.lang.String property) {
        propertyDao.delete(property);
    }

    @Override
    public void updateProperty(Property property) {
        propertyDao.update(property);
    }

    @Override
    public Property loadPropertyById(Integer idProperty,  boolean optimize) {

        Property property = propertyDao.getPropertyById(idProperty);

        if(!optimize) {
            List<PropertyOptionItem> optionItemList = loadPropertyOptionItem(property.getPropertyId());
            property.setPropertyOptionItems(optionItemList);
        }


        return property;
    }

    /*@Override
    public List<Property> loadAllProperty() {
        return propertyDao.getAllProperty();
    }*/

    @Override
    public List<Property> loadAllProperty() {
        List<Property> allProperty = propertyDao.getAllProperty();
        List<Property> propertyList = new ArrayList<>();
        for (Property property : allProperty) {
            List<PropertyOptionItem> optionItemList = propertyOptionItemDao.getPropertyOptionItemById(property.getPropertyId());
            property.setPropertyOptionItems(optionItemList);
            propertyList.add(property);
        }

        return propertyList;
    }

    @Override
    public List<Property> findAllPropertyByIds(List idsProperty) {
        return propertyDao.getAllPropertyByIds(idsProperty);


    /** Section Property Item Options **/

    }@Override
     public void addOptionItem(PropertyOptionItem optionItem) {
        propertyOptionItemDao.save(optionItem);
    }


    @Override
    public void deleteOptionItem(PropertyOptionItem optionItem) {
        propertyOptionItemDao.delete(optionItem);
    }

    @Override
    public void updatePropertyOptionItem(PropertyOptionItem optionItemOld, float oldValue) {
        propertyOptionItemDao.update(optionItemOld, oldValue);
    }

    @Override
    public List<PropertyOptionItem> loadPropertyOptionItem(Integer idProperty) {
        return propertyOptionItemDao.getPropertyOptionItemById(idProperty);
    }



}
