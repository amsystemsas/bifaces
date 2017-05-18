package com.amsystem.bifaces.dynamictemplate.setting.services.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyItemLabelDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyOptionItemDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyTemplateDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.IFProperty;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItem;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItemLabel;
import com.amsystem.bifaces.dynamictemplate.setting.services.PropertyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
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

    private static final Logger log = LogManager.getLogger(PropertyServiceImpl.class.getName());



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

        for (IFProperty property : allProperty) {
            List<PropertyOptionItem> optionItemList = findPropertyOptionItem(property.getPropertyId());

            for (PropertyOptionItem poi : optionItemList){
                List<PropertyOptionItemLabel> propertyOptionItemLabel = findPropertyOptionItemLabel(poi.getPropertyId(), property.getPropertyId());
                Set<PropertyOptionItemLabel> propertyOptionItemLabelSet = new HashSet<>(propertyOptionItemLabel);
                poi.setItemLabels(propertyOptionItemLabelSet);
            }

            property.setPropertyOptionItems(optionItemList);
        }

        return allProperty;
    }


    public List<IFProperty> findAllPropertyByIds(List idsProperty) {
        return propertyDao.loadPropertyListByIdList(idsProperty);

    }


    public boolean isAssociate(IFProperty selectedProp) {
        return propertyTemplateDao.isPropertyAssociatedToTemplate(selectedProp.getPropertyId());
    }


    public boolean cloneProperty(String propertyCloneName, IFProperty selectedProp){
        boolean success = false;
        //1 Almacear la propiedad clonada
        if(propertyDao.saveManualTransaction(propertyCloneName)) {
            IFProperty cloneProperty = propertyDao.loadPropertyByName(propertyCloneName);
            //Actualizando valores de la propiedad clonada
            cloneProperty.setDefaultValue(selectedProp.getDefaultValue());
            cloneProperty.setEditable(selectedProp.isEditable());
            cloneProperty.setRequired(selectedProp.isRequired());
            cloneProperty.setVisible(selectedProp.isVisible());
            cloneProperty.setExpressionValidator(selectedProp.getExpressionValidator());
            cloneProperty.setLabel(selectedProp.getLabel());
            cloneProperty.setFormula(selectedProp.getFormula());
            cloneProperty.setMask(selectedProp.getMask());
            cloneProperty.setRenderingType(selectedProp.getRenderingType());
            cloneProperty.setParent(selectedProp.getParent());

            if(propertyDao.updateManualTransaction(cloneProperty)) {
                List<PropertyOptionItem> optionItemSelectedList = selectedProp.getPropertyOptionItems();
                List<PropertyOptionItem> optionItemCloneList = new ArrayList<>();

                for (PropertyOptionItem poi : optionItemSelectedList) {
                    optionItemCloneList.add(new PropertyOptionItem(cloneProperty.getPropertyId(), poi.getValue(), poi.getDescription(), null));
                }

                if(!optionItemCloneList.isEmpty()) {
                    if (propertyOptionItemDao.saveBatch(optionItemCloneList)) {
                        optionItemCloneList.clear();
                        int indexCloneList = -1;
                        optionItemCloneList = propertyOptionItemDao.loadPropertyOptionItem(cloneProperty.getPropertyId());

                        for (PropertyOptionItem poi : optionItemSelectedList) {
                            Set<PropertyOptionItemLabel> itemLabels = poi.getItemLabels();
                            //Clonando Labels de cada Item
                            if (itemLabels != null) {
                                Set<PropertyOptionItemLabel> itemLabelsNew = new HashSet<>();

                                for (PropertyOptionItem poiClone : optionItemCloneList) {
                                    if (poi.getDescription().equalsIgnoreCase(poiClone.getDescription())) {

                                        indexCloneList = optionItemCloneList.indexOf(poiClone);
                                        for (PropertyOptionItemLabel poil : itemLabels) {
                                            itemLabelsNew.add(new PropertyOptionItemLabel(poiClone.getPoiId(), poiClone.getPropertyId(), poil.getDescription(), poil.getLocale()));
                                        }

                                    }
                                }

                                if (indexCloneList > 0) {
                                    optionItemCloneList.remove(indexCloneList);
                                    indexCloneList = -1;
                                }

                                //Guardando lables clonados
                                if (!itemLabelsNew.isEmpty()) propertyItemLabelDao.saveBatch(itemLabelsNew);
                            }
                        }
                    }
                }
            }else{
                log.error("Error en la actualizacion de datos de la propiedad clonada.." + cloneProperty.getName());
            }
        }else {
            log.error("Error registrando la propiedad..." + propertyCloneName);
        }

        return success;
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
