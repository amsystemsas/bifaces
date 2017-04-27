package com.cobus.dynamictemplate.setting.dao;

import com.cobus.dynamictemplate.setting.model.DynamicObjectProperty;

import java.util.List;

/**
 * Title: IFDynamicObjectPropDao.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public interface DynamicObjectPropDao {
    
    void save(Integer idProperty, Integer idDynamicObject, String templateName);

    void delete(Integer idProperty, Integer idDynamicObject, String templateName);

    DynamicObjectProperty getDynamicObjectPropertyById(Integer idProperty, Integer idDynamicObject);

    List<Integer> getPropertyIdList(Integer idDynamicObject);

      

}
