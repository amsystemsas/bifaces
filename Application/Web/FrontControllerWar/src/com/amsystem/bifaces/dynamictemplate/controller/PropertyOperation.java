package com.amsystem.bifaces.dynamictemplate.controller;

import com.amsystem.bifaces.dynamictemplate.setting.model.IFProperty;
import com.amsystem.bifaces.util.NotificationType;
import com.amsystem.bifaces.UserInfo;
import com.amsystem.bifaces.dynamictemplate.setting.model.Property;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItem;
import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.dynamictemplate.setting.services.PropertyService;
import com.amsystem.bifaces.dynamictemplate.setting.services.PropertyTemplateService;
import com.amsystem.bifaces.dynamictemplate.setting.services.TemplateService;
import com.amsystem.bifaces.util.MessageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Title: PropertyOperation.java
 *
 * @author jaguilar (JAR)
 * File Creation on 14/05/2016
 */

@Controller
@ViewScoped
@ManagedBean(name = "propertyOperation")
public class PropertyOperation implements Serializable{

    private static final Logger log = LogManager.getLogger(PropertyOperation.class.getName());

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyTemplateService propertyTemplateService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ResourceBundle rb;

    public PropertyService getPropertyService() {
        return propertyService;
    }

    public void setPropertyService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    public PropertyTemplateService getPropertyTemplateService() {
        return propertyTemplateService;
    }

    public void setPropertyTemplateService(PropertyTemplateService propertyTemplateService) {
        this.propertyTemplateService = propertyTemplateService;
    }

    public TemplateService getTemplateService() {
        return templateService;
    }

    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * Agrega una nueva propiedad en el sistema
     * @param propertyName Nombre de la nueva propiedad
     */
    public void addProperty(String propertyName) {
        IFProperty property = new Property();
        property.setName(propertyName);
        if(propertyService.addProperty(propertyName)){
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), rb.getString("property.save.success_TT")) ;
        }else{
            MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), rb.getString("property.error.save_TT"));
        }

    }

    /**
     * Actualiza en el sistema los valores de la propiedad seleccionada
     * @param selectedProp propiedad seleccionada en el sistema
     */
    public void updateProperty(IFProperty selectedProp) {
        propertyService.updateProperty(selectedProp);
    }

    /**
     * Elimina la propiedad seleccionda en el sistema. Si la propiedad se encuentra asociada a una plantilla arroja un
     * mensaja indicando que no se puede eliminar. Primero debe ser desvinculada de la plantilla
     * @param selectedProperty
     */
    public void deleteProperty(IFProperty selectedProperty){

        if(!propertyService.deleteProperty(selectedProperty)){
            List<Integer> templateListByProperty = propertyTemplateService.findTemplateListByProperty(selectedProperty.getPropertyId());
            List<Template> allTemplateByIdList = templateService.findAllTemplateByIdList(templateListByProperty);
            log.error("No se puede eliminar la propiedad porque se sencuentra asociada a las siguientes plantillas: ");
            for(Template template : allTemplateByIdList){
                log.error("Plantilla: " + template.getName());
            }
        }
    }
    

    public List<IFProperty> propertyList(){
        log.debug("Cargando Propiedades..");
        log.debug("Lenguaje: " + UserInfo.getLocaleUser());
        log.debug("Lenguaje_ L: " + Locale.getDefault());
        log.debug("Bundle = " + rb.getString("save_TT"));
        List<IFProperty> propList = propertyService.findAllProperty();

        /*
        for (Property prop : propertyService.loadAllProperty()) {
            propList.add(new PropertyVO(prop.getPropertyId(),prop.getName(), prop.getLabel(), prop.getType(),
                    prop.getRenderingType(), prop.getExpressionValidator(), prop.getFormula(), prop.getDefaultValue(),
                    prop.isVisible(), prop.isEditable(), prop.isRequired(), prop.getParent(), prop.getMask()));

        }
        */
        return propList;
    }


    public void addOptionItem(PropertyOptionItem optionItem) {
        propertyService.addOptionItemProperty(optionItem);
    }

    public List<PropertyOptionItem> getAllPropertyOptionItem(Integer propertyId) {
        return propertyService.findPropertyOptionItem(propertyId);
    }


}
