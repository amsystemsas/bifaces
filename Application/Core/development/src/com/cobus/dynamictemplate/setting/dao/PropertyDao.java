package com.cobus.dynamictemplate.setting.dao;

import com.cobus.dynamictemplate.setting.model.IFProperty;
import com.cobus.dynamictemplate.setting.model.Property;

import java.util.List;

/**
 * Title: PropertyDao.java
 *
 * @author jaguilar (JAR) 
 * File Creation on 03/04/2016
 */
public interface PropertyDao {

    /**
     * Guarda una nueva propiedad en la base de datos
     *
     * @param propertyName nombre de la nueva propiedad
     * @return <tt>true</tt> Si la propiedad es almacenada con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean save(String propertyName);

    /**
     * Siendo un nombre unico por propiedad, el metodo elimina una propiedad exitente en el sistema a partir del nombre
     * que recibe como parametro
     *
     * @param propertyName Nombre de la propiedad a ser eliminada.
     * @return <tt>true</tt> Si la propiedad es eliminada con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean delete(String propertyName);

    /**
     * Actualiza los valores de la propiedad pasada por parametro
     *
     * @param property propiedad a ser actualizada
     */
    boolean update(IFProperty property);

    /**
     * Carga una propiedad de la base de datos que coincida con el identificador que recibe por parametro
     * @param propertyId identificador a buscar en la base de datos
     * @return <tt>Propiedad</tt>
     */
    IFProperty loadPropertyById(Integer propertyId);

    /**
     * Carga todas las propiedades almacenadas en la base de datos
     * @return <tt>List</tt> de propiedades
     */
    List<IFProperty> loadAllProperty();

    /**
     * Carga un conjunto de propiedades que coincida con los identificadores que recibe como parametro
     * @param propertyId Lista de identificadores
     * @return <tt>List</tt> de propiedades
     */
    List<IFProperty> loadPropertyListByIdList(List propertyId);

}
