package com.cobus.dynamictemplate.setting.dao;

import com.cobus.dynamictemplate.setting.model.PropertyOptionItem;

import java.util.List;

/**
 * Title: PropertyOptionItem.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 27/07/2016.
 */
public interface PropertyOptionItemDao {

    void save(PropertyOptionItem optionItem);

    void delete(PropertyOptionItem optionItem);

    void update(PropertyOptionItem optionItemOld, float oldValue);

    PropertyOptionItem getOptionItemByDescription(PropertyOptionItem optionItem);

    List<PropertyOptionItem> getPropertyOptionItemById(Integer propertyId);



}
