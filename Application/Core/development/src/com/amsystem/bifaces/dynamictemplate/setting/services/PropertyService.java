package com.amsystem.bifaces.dynamictemplate.setting.services;

import com.amsystem.bifaces.dynamictemplate.setting.model.IFProperty;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItem;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItemLabel;

import java.util.List;

/**
 * Title: PropertyService.java
 * @author jaguilar (JAR)
 * File Creation on 09/04/2016
 */
public interface PropertyService {
    /**
     * Agrega una nueva propedad al negocio
     * @param propertyName nombre de la nueva propiedad
     * @return <tt>true</tt> Si la propiedad es agregada con exito. <tt>false</tt> Si ocurre algun error.
     */
    boolean addProperty(String propertyName);

    boolean addOptionItemProperty(PropertyOptionItem optionItem);

    boolean addItemLabelProperty(PropertyOptionItemLabel optionItemLabel);


    boolean deleteProperty(IFProperty property);

    boolean deleteOptionItemProperty(PropertyOptionItem optionItem);

    boolean deleteItemLabelProperty(PropertyOptionItemLabel optionItemLabel);



    boolean updateProperty(IFProperty property);

    boolean updateOptionItemProperty(PropertyOptionItem propertyOptionItem);

    boolean updateItemLabelProperty(PropertyOptionItemLabel propertyOptionItemLabel);


    IFProperty findPropertyById(Integer idProperty, boolean optimize);

    List<PropertyOptionItem> findPropertyOptionItem(Integer propertyId);

    List<PropertyOptionItemLabel> findPropertyOptionItemLabel(Integer poiId, Integer propertyId);

    List<IFProperty> findAllProperty();

    List<IFProperty> findAllPropertyByIds(List idsProperty);


    boolean isAssociate(IFProperty selectedProp);

    boolean cloneProperty(String propertyCloneName, IFProperty selectedProp);












}
