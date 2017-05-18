package com.amsystem.bifaces.dynamictemplate.setting.services.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyTemplateDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: PropertyTemplateBo.java
 * @author jaguilar (JAR)
 * File Creation on 15/05/2016
 */

@Service("propertyTemplateService")
@Transactional
public class PropertyTemplateService implements com.amsystem.bifaces.dynamictemplate.setting.services.PropertyTemplateService {
    
    @Autowired
    private PropertyTemplateDao propertyTemplateDao;

    
    @Override
    public boolean addPropertyToTemplate(PropertyTemplate propertyTemplate) {
        return propertyTemplateDao.save(propertyTemplate);
    }

    @Override
    public boolean deletePropertyToTemplate(PropertyTemplate propertyTemplate) {
        return propertyTemplateDao.delete(propertyTemplate);
    }

    @Override
    public boolean isPropertyAssociated(Integer propertyId) {
        return propertyTemplateDao.isPropertyAssociatedToTemplate(propertyId);
    }

    @Override
    public boolean hasTemplateProperties(Integer templateId) {
        return propertyTemplateDao.hasTemplateProperties(templateId);
    }

    @Override
    public List<Integer> findTemplateListByProperty(Integer propertyId) {
        return propertyTemplateDao.loadTemplateByPropertyId(propertyId);
    }

    @Override
    public List<Integer> findPropertyListByTemplate(Integer templateId) {
        return propertyTemplateDao.loadPropertyByTemplateId(templateId);
    }
}
