package com.amsystem.bifaces.dynamictemplate.setting.dao;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.dynamictemplate.util.TemplateStatus;
import com.amsystem.bifaces.util.CategoryName;

import java.util.List;

/**
 * Title: DynamicObjectDao.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public interface TemplateDao {

    boolean saveTemplate(Template template);

    boolean deleteTemplate(Template template);

    boolean updateTemplate(Template template);

    Template loadTemplateById(Integer idTr);

    Template loadTemplateByName(String templateName);
    
    List<Template> loadTemplateByStatus(TemplateStatus templateStatus);

    List<Template> loadTemplateByCategory(CategoryName categoryName);

    List<Template> loadAllTemplate();

    /**
     * Carga un conjunto de propiedades que coincida con los identificadores que recibe como parametro
     * @param templateId Lista de identificadores
     * @return <tt>List</tt> de propiedades
     */
    List<Template> loadPropertyListByIdList(List templateId);

    Integer getCountRowTemplate(Template template);

}
