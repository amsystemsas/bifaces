package com.amsystem.bifaces.dynamictemplate.setting.services.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyItemLabelDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyOptionItemDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyTemplateDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.IFProperty;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItem;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItemLabel;
import com.amsystem.bifaces.dynamictemplate.setting.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Title: PropertyBoImpl.java
 * @author jaguilar (JAR)
 * File Creation on 09/04/2016
 */
@Service("propertyService")
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDao propertyDao;

    @Autowired
    private PropertyOptionItemDao propertyOptionItemDao;

    @Autowired
    private PropertyItemLabelDao propertyItemLabelDao;

    @Autowired
    private PropertyTemplateDao propertyTemplateDao;



    public boolean addProperty(String propertyName) {
        return propertyDao.save(propertyName);
    }

    public boolean addOptionItemProperty(PropertyOptionItem optionItem){
        return propertyOptionItemDao.save(optionItem);
    }

    public boolean addItemLabelProperty(PropertyOptionItemLabel optionItemLabel){
        return propertyItemLabelDao.save(optionItemLabel);
    }


    public boolean deleteProperty(IFProperty property) {
        boolean success = false;
        if(!isAssociate(property))
            success = propertyDao.delete(property.getName());

        return success;
    }

    public boolean deleteOptionItemProperty(PropertyOptionItem optionItem) {
        return propertyOptionItemDao.delete(optionItem);
    }

    public boolean deleteItemLabelProperty(PropertyOptionItemLabel optionItemLabel) {
        return propertyItemLabelDao.delete(optionItemLabel.getPoilId());
    }


    public boolean updateProperty(IFProperty property) {
        return propertyDao.update(property);
    }

    public boolean updateOptionItemProperty(PropertyOptionItem propertyOptionItem) {
        return propertyOptionItemDao.updateOptionItem(propertyOptionItem);
    }

    public boolean updateItemLabelProperty(PropertyOptionItemLabel propertyOptionItemLabel) {
        return propertyItemLabelDao.update(propertyOptionItemLabel);
    }


    public IFProperty findPropertyById(Integer idProperty,  boolean optimize) {

        IFProperty property = propertyDao.loadPropertyById(idProperty);

        if(!optimize) {
            List<PropertyOptionItem> optionItemList = findPropertyOptionItem(property.getPropertyId());
            for (PropertyOptionItem poi : optionItemList){
                List<PropertyOptionItemLabel> propertyOptionItemLabel = findPropertyOptionItemLabel(poi.getPropertyId(), property.getPropertyId());
                poi.setItemLabels((Set<PropertyOptionItemLabel>) propertyOptionItemLabel);
            }
            property.setPropertyOptionItems(optionItemList);
        }

        return property;
    }


    @Override
    public List<PropertyOptionItem> findPropertyOptionItem(Integer propertyId) {
        return propertyOptionItemDao.loadPropertyOptionItem(propertyId);
    }


    @Override
    public List<PropertyOptionItemLabel> findPropertyOptionItemLabel(Integer poiId, Integer propertyId) {
        return propertyItemLabelDao.loadAllPropertyItemLabelById(poiId,propertyId);
    }


    @Override
    public List<IFProperty> findAllProperty() {
        List<IFProperty> allProperty = propertyDao.loadAllProperty();
        //List<IFProperty> propertyList = new ArrayList<>();
        for (IFProperty property : allProperty) {
            List<PropertyOptionItem> optionItemList = findPropertyOptionItem(property.getPropertyId());

            for (PropertyOptionItem poi : optionItemList){
                List<PropertyOptionItemLabel> propertyOptionItemLabel = findPropertyOptionItemLabel(poi.getPropertyId(), property.getPropertyId());
                poi.setItemLabels((Set<PropertyOptionItemLabel>) propertyOptionItemLabel);
            }

            property.setPropertyOptionItems(optionItemList);
          //  propertyList.add(property);
        }

        return allProperty;
    }


    public List<IFProperty> findAllPropertyByIds(List idsProperty) {
        return propertyDao.loadPropertyListByIdList(idsProperty);

    }

    public boolean isAssociate(IFProperty selectedProp) {
        return propertyTemplateDao.isPropertyAssociatedToTemplate(selectedProp.getPropertyId());
    }


    public PropertyDao getPropertyDao() {
        return propertyDao;
    }

    public void setPropertyDao(PropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    public PropertyOptionItemDao getPropertyOptionItemDao() {
        return propertyOptionItemDao;
    }

    public void setPropertyOptionItemDao(PropertyOptionItemDao propertyOptionItemDao) {
        this.propertyOptionItemDao = propertyOptionItemDao;
    }

    public PropertyItemLabelDao getPropertyItemLabelDao() {
        return propertyItemLabelDao;
    }

    public void setPropertyItemLabelDao(PropertyItemLabelDao propertyItemLabelDao) {
        this.propertyItemLabelDao = propertyItemLabelDao;
    }

    public PropertyTemplateDao getPropertyTemplateDao() {
        return propertyTemplateDao;
    }

    public void setPropertyTemplateDao(PropertyTemplateDao propertyTemplateDao) {
        this.propertyTemplateDao = propertyTemplateDao;
    }
}
