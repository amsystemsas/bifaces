package com.amsystem.bifaces.dynamictemplate.setting.dao;

import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyTemplate;

import java.util.List;

/**
 * Title: PropertyTemplateDao.java
 *
 * @author Jaime Aguilar (JAR)
 * File Creation on 24/04/2016
 */
public interface PropertyTemplateDao {

    /**
     * Guarda una asociacion entre <tt>Template</tt> y <tt>Property</tt>
     *
     * @param propertyTemplate
     */
    boolean save(PropertyTemplate propertyTemplate);

    /**
     * Guarda en base de datos el conjunto de propiedades asociadas a una  plantilla
     *
     * @param propertyOptionItems <tt>List</tt> de propiedades a ser almacenados
     * @return <tt>true</tt> Si el el conjunto de asociaciones es almacenado con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean saveBatch(List<PropertyTemplate> propertyOptionItems);

    /**
     * Elimina una asociacion entre <tt>Template</tt> y <tt>Property</tt>
     *
     * @param propertyTemplate
     */
    boolean delete(PropertyTemplate propertyTemplate);

    /**
     * Verifica si el identificador pasado por parametro se encuentra vinculado a una plantilla
     *
     * @param propertyId identificador de una <tt>Propiedad</tt>
     * @return
     */
    boolean isPropertyAssociatedToTemplate(Integer propertyId);

    /**
     * Verifica si una plantilla tiene propiedades asociadas
     *
     * @param templateId
     * @return
     */
    boolean hasTemplateProperties(Integer templateId);

    /**
     * Busca una asociacion dado el identificador de Propidad y Plantilla
     * TODO: Metodo de poca utilidad hasta ahora
     *
     * @param idProperty
     * @param idDynamicObject
     * @return
     */
    PropertyTemplate getDynamicObjectPropertyById(Integer idProperty, Integer idDynamicObject);

    /**
     * Carga todos los identificadores de las propiedades que se encuentran asociada una plantilla
     *
     * @param templateId
     * @return
     */
    List<Integer> loadPropertyByTemplateId(Integer templateId);

    /**
     * Carga todas las plantillas a las que se encuentra asociada una propiedad.
     *
     * @param propertyId
     * @return
     */
    List<Integer> loadTemplateByPropertyId(Integer propertyId);


      

}
