package com.amsystem.bifaces.dynamictemplate.setting.services.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyTemplateDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.TemplateDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.IFProperty;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyTemplate;
import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.dynamictemplate.setting.bo.PropertyTree;
import com.amsystem.bifaces.dynamictemplate.setting.services.TemplateService;
import com.amsystem.bifaces.util.CategoryName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Title: DynamicObjectService.java
 *
 *
 *
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */



@Service("templateService")
@Transactional
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private PropertyDao propertyDao;

    @Autowired
    private PropertyTemplateDao propertyTemplateDao;


    @Override
    public boolean addTemplate(Template template) {
        return templateDao.saveTemplate(template);
    }

    @Override
    public boolean deleteTemplate(Template template) {
        boolean success = false;
        //if(!propertyTemplateDao.hasTemplateProperties(template.getTemplateId()))
            success = templateDao.deleteTemplate(template);

        return success;
    }

    @Override
    public boolean cloneTemplate(String cloneTemplateName, Template selectedTemplate, CategoryName fromCategory) {
        Template cloneTemplate = new Template(cloneTemplateName, fromCategory.getValue());
        cloneTemplate.setStatus(selectedTemplate.getStatus());

        if(addTemplate(cloneTemplate)){
            cloneTemplate = templateDao.loadTemplateByName(cloneTemplateName);
            List<IFProperty> propertyList = selectedTemplate.getPropertyList();
            List<PropertyTemplate> propertyTemplateList;

            if(propertyList != null && !propertyList.isEmpty()){
                propertyTemplateList = new ArrayList<>();

                for (IFProperty property : propertyList){
                    propertyTemplateList.add(new PropertyTemplate(property.getPropertyId(), cloneTemplate.getTemplateId(), new Date()));
                }

                propertyTemplateDao.saveBatch(propertyTemplateList);

            }


        }
        return false;
    }

    @Override
    public Template findTemplateById(Integer templateId) {
        Template template = templateDao.loadTemplateById(templateId);

        List<Integer> propertyIdList = propertyTemplateDao.loadPropertyByTemplateId(template.getTemplateId());
        List<IFProperty> propertyList = propertyDao.loadPropertyListByIdList(propertyIdList);
        template.setPropertyList(propertyList);

        return template;
    }

    @Override
    public Template findTemplateByName(String templateName) {
        Template template = templateDao.loadTemplateByName(templateName);

        List<Integer> propertyIdList = propertyTemplateDao.loadPropertyByTemplateId(template.getTemplateId());
        List<IFProperty> propertyList = propertyDao.loadPropertyListByIdList(propertyIdList);
        template.setPropertyList(propertyList);

        return template;
    }

    @Override
    public List<Template> findAllTemplate() {
        return templateDao.loadAllTemplate();
    }

    @Override
    public List<Template> findAllTemplateByIdList(List templateId) {
        return templateDao.loadTemplateListByIdList(templateId);
    }

    @Override
    public HashMap<Template, List<PropertyTree>> findFullTemplateProperty() {
        List<Template> templateList = findAllTemplate();

        List<PropertyTree> propertyTreeList;
        HashMap<Template, List<PropertyTree>> templateMap = new HashMap<>();

        for (Template template : templateList) {
            List<Integer> propertyIdList = propertyTemplateDao.loadPropertyByTemplateId(template.getTemplateId());
            propertyTreeList = new ArrayList<>();

            if(!propertyIdList.isEmpty()) {
                List<IFProperty> propertyList = propertyDao.loadPropertyListByIdList(propertyIdList);
                for (IFProperty property : propertyList) {
                    propertyTreeList.add(new PropertyTree(property.getPropertyId(), property.getName()));
                }
            }

            templateMap.put(template, propertyTreeList);
        }

        return templateMap;
    }


}
