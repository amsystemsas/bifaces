package com.cobus.dynamictemplate.setting.services.impl;

import com.cobus.dynamictemplate.setting.bo.PropertyTree;
import com.cobus.dynamictemplate.setting.dao.DynamicObjectDao;
import com.cobus.dynamictemplate.setting.dao.DynamicObjectPropDao;
import com.cobus.dynamictemplate.setting.dao.IPropertyDao;
import com.cobus.dynamictemplate.setting.model.DynamicObject;
import com.cobus.dynamictemplate.setting.model.Property;
import com.cobus.dynamictemplate.setting.services.IDynamicObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Title: DynamicObjectBoImpl.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */

@Service("dynamicObjectService")
@Transactional
public class DynamicObjectService implements IDynamicObjectService {

    @Autowired
    private DynamicObjectDao dao;

    @Autowired
    private IPropertyDao IPropertyDao;

    @Autowired
    private DynamicObjectPropDao dynamicObjectPropDao;

    public IPropertyDao getIPropertyDao() {
        return IPropertyDao;
    }

    public void setIPropertyDao(IPropertyDao IPropertyDao) {
        this.IPropertyDao = IPropertyDao;
    }

    public DynamicObjectPropDao getDynamicObjectPropDao() {
        return dynamicObjectPropDao;
    }

    public void setDynamicObjectPropDao(DynamicObjectPropDao dynamicObjectPropDao) {
        this.dynamicObjectPropDao = dynamicObjectPropDao;
    }

    public DynamicObjectDao getDao() {
        return dao;
    }

    public void setDao(DynamicObjectDao dao) {
        this.dao = dao;
    }
    
    @Override
    public void addTemplate(DynamicObject dynamicObject) {
        dao.saveDO(dynamicObject);
    }

    @Override
    public void deleteTemplate(DynamicObject dynamicObject) {
        dao.deleteDO(dynamicObject);
    }

    @Override
    public void updateTemplate(DynamicObject dynamicObject) {
        dao.updateDO(dynamicObject);
    }

    @Override
    public DynamicObject loadTemplateById(Integer idDO) {
        return dao.getDynamicObjectById(idDO);
    }

    @Override
    public DynamicObject loadTemplateByName(String templateName) {
        return dao.getDynamicObjectByName(templateName);
    }

    @Override
    public List<DynamicObject> loadAllTemplate() {
        return dao.getDynamicObjectList();
    }

    /*@Override
    public HashMap<DynamicObject, List<PropertyBean>> loadAllTemplateProperty() {
        return dao.getAllDynamicObject();
    }*/

    @Override
    public HashMap<DynamicObject, List<PropertyTree>> loadAllTemplateProperty() {
        List<DynamicObject> dynamicObjectList = loadAllTemplate();

        List<PropertyTree> propertyList;
        HashMap<DynamicObject, List<PropertyTree>> templateMap = new HashMap<>();

        for (DynamicObject dynamicObject : dynamicObjectList) {
            List<Integer> allPropertyIdByDynamicObject = dynamicObjectPropDao.getPropertyIdList(dynamicObject.getDoId());
            propertyList = new ArrayList<>();

            for (Integer idProperty : allPropertyIdByDynamicObject) {
                Property property = IPropertyDao.getPropertyById(idProperty);
                propertyList.add(new PropertyTree(property.getPropertyId(), property.getName()));

            }

            templateMap.put(dynamicObject, propertyList);
        }

        return templateMap;
    }

    @Override
    public DynamicObject loadTemplate(Integer category, String templateName) {
        return dao.getTemplate(category, templateName);
    }
}
