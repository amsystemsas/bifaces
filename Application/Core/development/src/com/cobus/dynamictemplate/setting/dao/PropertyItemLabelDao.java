package com.cobus.dynamictemplate.setting.dao;

import com.cobus.dynamictemplate.setting.model.PropertyOptionItemLabel;

import java.util.List;

/**
 * Title: PropertyItemLabelDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 01/05/2017.
 */


public interface PropertyItemLabelDao {

    /**
     * Guarda una nueva etiqueta en la base de datos perteneciente a un <tt>Item</tt>
     *
     * @return <tt>true</tt> Si la etiqueta es almacenada con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean save(PropertyOptionItemLabel propertyOptionItemLabel);

    /**
     * Dado un identificador, elimina la etiqueta que coincida con el identificador.
     *
     * @param poilId    identificador de la etiqueta
     *
     * @return <tt>true</tt> Si la etiqueta es eliminada con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean delete(Integer poilId);

    /**
     * Actualiza los valores <tt>Value</tt> y <tt>Locale</tt> de una etiqueta
     *
     * @param propertyOptionItemLabel etiqueta a ser actualizada
     *
     *@return <tt>true</tt> Si la etiqueta es actualizada con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean update(PropertyOptionItemLabel propertyOptionItemLabel);


    /**
     * Carga todas las etiquetas asociadas a un <tt>PropertyOptionItem</tt>
     *
     * @return <tt>List</tt> de etiquetas.
     */
    List<PropertyOptionItemLabel> loadAllPropertyItemLabelById(Integer propertyOptionItemId, Integer propertyId);

    /**
     * Carga una etiqueta de la base de datos que coincida con el identificador que recibe por parametro
     *
     * @param poilId identificador de la etiqueta
     *
     * @return <tt>Etiqueta</tt>
     */
    PropertyOptionItemLabel loadPropertyItemLabelById(Integer poilId);


}
