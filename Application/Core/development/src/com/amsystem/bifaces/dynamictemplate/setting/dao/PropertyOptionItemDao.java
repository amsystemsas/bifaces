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
     * Guarda un nuevo <tt>OptionItem</tt> en la base de datos
     *
     * @param optionItem Item a almacenar
     * @return <tt>true</tt> Si el Item es almacenado con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean save(PropertyOptionItem optionItem);

    /**
     * Guarda un nuevo <tt>OptionItem</tt> en la base de datos con transaccion y conexion controlada manualmente
     *
     * @param optionItem
     * @return
     */
    boolean saveManualTransaction(PropertyOptionItem optionItem);

    /**
     * Guarda en base de datos el conjunto de <tt>OptionItem</tt> que recibe por parametro
     *
     * @param propertyOptionItems <tt>List</tt> de <tt>OptionItem</tt> a ser almacenados
     * @return <tt>true</tt> Si el el conjunto de <tt>OptionItem</tt> es almacenado con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean saveBatch(List<PropertyOptionItem> propertyOptionItems);

    /**
     * Elimina de la base de datos un <tt>OptionItem</tt> que recibe como parametro
     *
     * @param optionItem <tt>OptionItem</tt> a ser eliminado
     * @return <tt>true</tt> Si el <tt>OptionItem</tt> es eliminado con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean delete(PropertyOptionItem optionItem);

    /**
     * Actualiza los valores en base de datos del <tt>OptionItem</tt> que recibe por parametro
     *
     * @param propertyOptionItem <tt>OptionItem</tt> a ser actualizado
     * @return <tt>true</tt> Si el <tt>OptionItem</tt> es actualizado con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean updateOptionItem(PropertyOptionItem propertyOptionItem);

    /**
     * Carga los <tt>OptionItem</tt> de una propiedad que coincidad con el identificador que recibe por parametro
     *
     * @param propertyId Identificador de la propiedad
     * @return <tt>List</tt> de <tt>OptionItem</tt> si existen registros con el identificador. <tt>List Empty</tt> si no existen registros.
     */
    List<PropertyOptionItem> loadPropertyOptionItem(Integer propertyId);



}
