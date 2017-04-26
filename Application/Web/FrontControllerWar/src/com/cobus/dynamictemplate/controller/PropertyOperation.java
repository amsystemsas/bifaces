package com.cobus.dynamictemplate.controller;

import com.cobus.UserInfo;
import com.cobus.dynamictemplate.setting.model.Property;
import com.cobus.dynamictemplate.setting.model.PropertyOptionItem;
import com.cobus.dynamictemplate.setting.services.IPropertyService;
import com.cobus.dynamictemplate.vo.PropertyVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Title: PropertyOperation.java
 * @author jaguilar (JAR)
 * File Creation on 14/05/2016
 */

@Controller
@ViewScoped
@ManagedBean(name = "propertyOperation")
public class PropertyOperation implements Serializable{

    @Autowired
    private IPropertyService propertyService;

    @Autowired
    ResourceBundle rb;

    private static final Logger log = LogManager.getLogger(PropertyOperation.class.getName());

    public IPropertyService getPropertyService() {
        return propertyService;
    }

    public void setPropertyService(IPropertyService propertyService) {
        this.propertyService = propertyService;
    }
    
    
    //add a new property data into database
    public void addProperty(String name) {
        Property property = new Property();
        property.setName(name);
        propertyService.addProperty(name);

    }
    
    public void updateProperty(Property selectedProp) {
        propertyService.updateProperty(selectedProp);
    }

    
    public void deleteProperty(String name){
        propertyService.deleteProperty(name);
    }
    
    public List<PropertyVO> getAllProperty(){
        log.debug("Cargando Propiedades..");
        log.debug("Lenguaje: " + UserInfo.getLocaleUser());
        log.debug("Lenguaje_ L: " + Locale.getDefault());
        log.debug("Bundle = " + rb.getString("save_TT"));
        List<PropertyVO> propList = new ArrayList();

        for (Property prop : propertyService.loadAllProperty()) {
            propList.add(new PropertyVO(prop.getPropertyId(),prop.getName(), prop.getLabel(), prop.getType(),
                    prop.getRenderingType(), prop.getExpressionValidator(), prop.getFormula(), prop.getDefaultValue(),
                    prop.isVisible(), prop.isEditable(), prop.isRequired(), prop.getParent(), prop.getMask()));
            
        }
        return propList;
    }


    public void addOptionItem(PropertyOptionItem optionItem) {
        propertyService.addOptionItem(optionItem);
    }

    public List<PropertyOptionItem> getAllPropertyOptionItem(Integer propertyId) {
        return propertyService.loadPropertyOptionItem(propertyId);
    }


}
