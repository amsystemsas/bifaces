package com.cobus.dynamictemplate.setting.services;

import com.cobus.dynamictemplate.setting.bo.PropertyTree;
import com.cobus.dynamictemplate.setting.model.DynamicObject;

import java.util.HashMap;
import java.util.List;

/**
 * Title: IFDynamicObjectBo.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public interface IDynamicObjectService {
    
    void addTemplate(DynamicObject dynamicObject);

    void deleteTemplate(DynamicObject dynamicObject);

    void updateTemplate(DynamicObject dynamicObject);

    DynamicObject loadTemplateById(Integer idDO);

    DynamicObject loadTemplateByName(String templateName);
    
    List<DynamicObject> loadAllTemplate();

    HashMap<DynamicObject, List<PropertyTree>> loadAllTemplateProperty();

    DynamicObject loadTemplate(Integer category, String templateName);

}
