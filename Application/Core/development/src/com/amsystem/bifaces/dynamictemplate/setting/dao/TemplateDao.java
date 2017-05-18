package com.amsystem.bifaces.dynamictemplate.setting.dao;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.dynamictemplate.util.TemplateStatus;
import com.amsystem.bifaces.util.CategoryName;

import java.util.List;

/**
 * Title: DynamicObjectDao.java
 *
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public interface TemplateDao {

    /**
     * Guarda una nueva plantilla en la base de datos
     *
     * @param template plantilla que sera almacenada
     * @return <tt>true</tt> Si la plantilla es almacenada con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean saveTemplate(Template template);

    /**
     * Elimina una plantilla exitente en el sistema.
     *
     * @param template plantilla que sera eliminada
     * @return <tt>true</tt> Si la plantilla es eliminada con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean deleteTemplate(Template template);

    /**
     * Actualiza los valores de la plantilla que recibe por parametro
     *
     * @param template plantilla cuyos valores seran actuaizados
     * @return <tt>true</tt> Si la plantilla es actualizada con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean updateTemplate(Template template);

    /**
     * Carga de la base de datos la plantilla que coincida con el identificador que recibe por parametro
     *
     * @param idTr identificador de la plantilla que sera cargada
     * @return <tt>Template</tt>
     */
    Template loadTemplateById(Integer idTr);

    /**
     * Carga de la base de datos la plantilla que coincida con el nombre que recibe por parametro
     *
     * @param templateName nombre de la plantilla
     * @return <tt>Template</tt>
     */
    Template loadTemplateByName(String templateName);

    /**
     * Carga de la base de datos todas la plantillas que coincida con el <tt>Status</tt> que recibe el metodo por parametro
     *
     * @param templateStatus <tt>status</tt> de la plantilla
     * @return <tt>List</tt> de plantillas
     */
    List<Template> loadTemplateByStatus(TemplateStatus templateStatus);

    /**
     * Carga de la base de datos todas la plantillas pertenecientes a la categoria que recibe el metodo por parametro
     *
     * @param categoryName categoria de la plantilla
     * @return <tt>List</tt> de plantillas
     */
    List<Template> loadTemplateByCategory(CategoryName categoryName);

    /**
     * Carga todas la plantillas almacenadas en la base de datos
     *
     * @return <tt>List</tt> de plantillas
     */
    List<Template> loadAllTemplate();

    /**
     * Carga un conjunto de plantilas que coincida con los identificadores que recibe como parametro
     *
     * @param templateId Lista de identificadores
     * @return <tt>List</tt> de propiedades
     */
    List<Template> loadTemplateListByIdList(List templateId);

    /**
     * Cuenta los registros que posee el <tt>Template</tt> que recibe por parametro
     *
     * @param template plantilla a la que se verificara el numero de registros
     * @return cantidad de registro de la plantilla
     */
    Integer getCountRowTemplate(Template template);

}
