package com.cobus.dynamictemplate.setting.services.impl;

import com.cobus.dynamictemplate.setting.dao.DynamicObjectPropDao;
import com.cobus.dynamictemplate.setting.services.IPropertyTemplateService;
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
public class PropertyTemplateService implements IPropertyTemplateService {
    
    @Autowired
    private DynamicObjectPropDao dynamicObjectPropDao;

    
    @Override
    public void addPropertyToTemplate(Integer idProperty, Integer idDynamicObject, String templateName) {
        dynamicObjectPropDao.save(idProperty, idDynamicObject, templateName);
    }

    @Override
    public void deletePropertyToTemplate(Integer idProperty, Integer idDynamicObject, String templateName) {
        dynamicObjectPropDao.delete(idProperty, idDynamicObject, templateName);
    }

    @Override
    public List<Integer> getPropertyIdList(Integer idDynamicObject) {
        return dynamicObjectPropDao.getPropertyIdList(idDynamicObject);
    }
    
    

    public DynamicObjectPropDao getDynamicObjectPropDao() {
        return dynamicObjectPropDao;
    }

    public void setDynamicObjectPropDao(DynamicObjectPropDao dynamicObjectPropDao) {
        this.dynamicObjectPropDao = dynamicObjectPropDao;
    }
    
    

}
