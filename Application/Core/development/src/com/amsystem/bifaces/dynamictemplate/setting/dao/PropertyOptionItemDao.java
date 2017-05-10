package com.amsystem.bifaces.dynamictemplate.setting.dao;

import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItem;

import java.util.List;

/**
 * Title: PropertyOptionItem.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 27/07/2016.
 */
public interface PropertyOptionItemDao {

    /**
     * Guarda un nuevo Item en la base de datos
     *
     * @param optionItem Item a almacenar
     * @return <tt>true</tt> Si el Item es almacenado con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean save(PropertyOptionItem optionItem);

    /**
     * Elimina de la base de datos el Item que recibe como parametro
     *
     * @param optionItem Item a ser eliminado
     * @return <tt>true</tt> Si el Item es eliminado con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean delete(PropertyOptionItem optionItem);

    /**
     * Actualiza los valores en base de datos del Item que recibe por parametro
     *
     * @param propertyOptionItem Item a ser actualizao
     * @return <tt>true</tt> Si el Item es actualizado con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean updateOptionItem(PropertyOptionItem propertyOptionItem);

    /**
     * Carga los Item de una propiedad cuyo identificador coincidad con el que recibe por parametro
     *
     * @param propertyId Identificador de la propiedad
     * @return <tt>List</tt> de Item si existen registros con el identificador. <tt>List Empty</tt> si no existen registros.
     */
    List<PropertyOptionItem> loadPropertyOptionItem(Integer propertyId);



}
