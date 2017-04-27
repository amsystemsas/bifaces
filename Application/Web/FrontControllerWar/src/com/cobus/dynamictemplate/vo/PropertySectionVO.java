package com.cobus.dynamictemplate.vo;

import com.cobus.dynamictemplate.controller.PropertyOperation;
import com.cobus.dynamictemplate.setting.model.PropertyOptionItem;
import com.cobus.dynamictemplate.util.RenderingType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Title: PropertySectionVO.java
 * @author jaguilar (JAR)
 * File Creation on 14/05/2016
 */

@ManagedBean(name = "propertySectionVO")
@ViewScoped
public class PropertySectionVO implements Serializable{
    private String propertyName;
    private String optionItemDescription;
    private String optionItemValue;
    private PropertyVO selectedProp;
    private PropertyOptionItem optionItem;

    @ManagedProperty("#{propertyOperation}")
    private PropertyOperation propertyOperation;

    private static final Logger log = LogManager.getLogger(PropertySectionVO.class.getName());

    /** Begin Section Getter and Setter Method **/

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getOptionItemDescription() {
        return optionItemDescription;
    }

    public void setOptionItemDescription(String optionItemDescription) {
        this.optionItemDescription = optionItemDescription;
    }

    public String getOptionItemValue() {
        return optionItemValue;
    }

    public void setOptionItemValue(String optionItemValue) {
        this.optionItemValue = optionItemValue;
    }

    public PropertyVO getSelectedProp() {
        return selectedProp;
    }

    public void setSelectedProp(PropertyVO selectedProp) {
        this.selectedProp = selectedProp;
    }

    public PropertyOptionItem getOptionItem() {
        return optionItem;
    }

    public void setOptionItem(PropertyOptionItem optionItem) {
        this.optionItem = optionItem;
    }



    /** Begin Section Operation Methods **/


    //add a new property data into database
    public void addProperty() {
        log.debug("Agregando Propiedad : " + getPropertyName());
        propertyOperation.addProperty(getPropertyName());
        setPropertyName(null);

    }


    public void addOptionItem() {
        PropertyOptionItem item = new PropertyOptionItem();
        item.setPropertyId(selectedProp.getPropertyId());
        item.setDescription(getOptionItemDescription());
        item.setValue(Float.valueOf(getOptionItemValue()));
        log.debug("Agregando Opcion: " + getOptionItem());
        propertyOperation.addOptionItem(item);
        setOptionItemDescription(null);
        setOptionItemValue(null);

    }


    public void updateProperty() {
        log.debug(" ************** Actualizando Propiedad *************: " + selectedProp.getName());
        propertyOperation.updateProperty(selectedProp);

    }


    public void deleteProperty(){
        log.debug("Eliminando Propiedad : " + selectedProp.getName());
        propertyOperation.deleteProperty(selectedProp.getName());
    }


    public List<PropertyVO> getAllProperty(){
        return propertyOperation.getAllProperty();
    }


    public List<PropertyOptionItem> getAllPropertyOptionItem(){ return propertyOperation.getAllPropertyOptionItem(selectedProp.getPropertyId());}


    public PropertyOperation getPropertyOperation() {
        return propertyOperation;
    }


    public void setPropertyOperation(PropertyOperation propertyOperation) {
        this.propertyOperation = propertyOperation;
    }


    public RenderingType[] getRenderingType(){
        return RenderingType.values();
    }
    

}
