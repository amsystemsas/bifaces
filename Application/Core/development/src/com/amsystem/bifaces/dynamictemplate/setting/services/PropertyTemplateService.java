package com.amsystem.bifaces.dynamictemplate.setting.services;

import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyTemplate;

import java.util.List;

/**
 * Title: IFPropertyTemplateBo.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public interface PropertyTemplateService {
    
    boolean addPropertyToTemplate(PropertyTemplate propertyTemplate);

    boolean deletePropertyToTemplate(PropertyTemplate propertyTemplate);

    boolean isPropertyAssociated(Integer propertyId);

    boolean hasTemplateProperties(Integer templateId);

    List<Integer> findTemplateListByProperty(Integer propertyId);

    List<Integer> findPropertyListByTemplate(Integer templateId);



}
