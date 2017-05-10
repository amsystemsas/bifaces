package com.cobus.dynamictemplate.setting.services;

import com.cobus.dynamictemplate.setting.bo.PropertyTree;
import com.cobus.dynamictemplate.setting.model.Template;

import java.util.HashMap;
import java.util.List;

/**
 * Title: IFDynamicObjectBo.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public interface TemplateService {
    
    boolean addTemplate(Template template);

    boolean deleteTemplate(Template template);

    Template findTemplateById(Integer templateId);

    Template findTemplateByName(String templateName);
    
    List<Template> findAllTemplate();

    List<Template> findAllTemplateByIdList(List templateId);

    HashMap<Template, List<PropertyTree>> findFullTemplateProperty();


}
