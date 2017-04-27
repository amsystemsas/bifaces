package com.cobus.dynamictemplate.setting.services;

import java.util.List;

/**
 * Title: IFPropertyTemplateBo.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public interface IPropertyTemplateService {
    
    void addPropertyToTemplate(Integer idProperty, Integer idDynamicObject, String templateName);

    void deletePropertyToTemplate(Integer idProperty, Integer idDynamicObject, String templateName);
    
    List<Integer> getPropertyIdList(Integer idDynamicObject);



}
