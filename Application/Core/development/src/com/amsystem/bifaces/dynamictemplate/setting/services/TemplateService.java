package com.amsystem.bifaces.dynamictemplate.setting.services;

import com.amsystem.bifaces.dynamictemplate.setting.bo.PropertyTree;
import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.util.CategoryName;

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

    boolean cloneTemplate(String cloneTemplateName, Template selectedTemplate, CategoryName fromCategory);

    Template findTemplateById(Integer templateId);

    Template findTemplateByName(String templateName);
    
    List<Template> findAllTemplate();

    List<Template> findAllTemplateByIdList(List templateId);

    HashMap<Template, List<PropertyTree>> findFullTemplateProperty();


}
