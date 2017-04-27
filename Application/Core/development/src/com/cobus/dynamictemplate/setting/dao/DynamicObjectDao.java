package com.cobus.dynamictemplate.setting.dao;

import com.cobus.dynamictemplate.setting.bo.PropertyTree;
import com.cobus.dynamictemplate.setting.model.DynamicObject;

import java.util.HashMap;
import java.util.List;

/**
 * Title: DynamicObjectDao.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public interface DynamicObjectDao {

    void saveDO(DynamicObject dynamicObject);

    void deleteDO(DynamicObject dynamicObject);

    void updateDO(DynamicObject dynamicObject);

    DynamicObject getDynamicObjectById(Integer idDO);

    DynamicObject getDynamicObjectByName(String templateName);
    
    List<DynamicObject> getDynamicObjectList();

    HashMap<DynamicObject, List<PropertyTree>> getAllDynamicObject();
    
    Integer getCountRow(DynamicObject dynamicObject);

    DynamicObject getTemplate(Integer category, String templateName);
}
