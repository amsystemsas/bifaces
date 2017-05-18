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
import com.amsystem.bifaces.util.SymbolType;
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
    private TemplateService templateService;

    @Autowired
    private PropertyTemplateService propertyTemplateService;

    @Autowired
    private ResourceBundle rb;


    /**
     * Agrega una nueva propiedad en el sistema
     * @param propertyName Nombre de la nueva propiedad
     */
    public void addProperty(String propertyName) {
        IFProperty property = new Property();
        property.setName(propertyName);
        if(propertyService.addProperty(propertyName)){
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), rb.getString("property_save_success_TT")) ;
        }else{
            MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), rb.getString("property_error_save_TT"));
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
     * Actualiza en el sistema los valores de la propiedad seleccionada
     * @param selectedProp propiedad seleccionada en el sistema
     */
    public void cloneProperty(String propertyCloneName, IFProperty selectedProp) {
        propertyService.cloneProperty(propertyCloneName, selectedProp);
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
            StringBuilder templateName = new StringBuilder();

            for(Template template : allTemplateByIdList){

                if(templateName.length() > 0) {
                    templateName.append(SymbolType.SPACE.getValue()).append(SymbolType.MINUS.getValue()).append(SymbolType.SPACE.getValue());
                }
                templateName.append(template.getName());
                log.error("Plantilla: " + template.getName());
            }

            String associatedMsjTt = rb.getString("propertyTemplateAssociated_msj_TT").concat(SymbolType.SPACE.getValue()).concat(templateName.toString());
            MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), associatedMsjTt) ;
        }else{
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), rb.getString("property_deleted_success_TT")) ;
        }
    }

    /**
     * Busca todas las propiedades registradas en el sistema
     * @return <tt>Lis</tt> de propiedades. Si no existe propiedades registradas, la <tt>List</tt> es vacia
     */
    public List<IFProperty> propertyList(){
        log.debug("Cargando Propiedades..");
        log.debug("Lenguaje: " + UserInfo.getLocaleUser());
        log.debug("Lenguaje_ L: " + Locale.getDefault());
        log.debug("Bundle = " + rb.getString("save_TT"));
        List<IFProperty> propList = propertyService.findAllProperty();

        return propList;
    }

    /**
     * Agrega un <tt>OptionItem</tt> a una propiedad de tipo <tt>List</tt>
     * @param selectedProperty propiedad seleccionada para agregar el nuevo <tt>OptionItem</tt>
     * @param optionItem nuevo <tt>OptionItem</tt>
     */
    public void addOptionItem(IFProperty selectedProperty, PropertyOptionItem optionItem) {
        if(propertyService.addOptionItemProperty(optionItem)){
            selectedProperty.setPropertyOptionItems(propertyService.findPropertyOptionItem(selectedProperty.getPropertyId()));
        }else {
            //Mostrar Mensaje
        }

    }

    /**
     *
     * @param selectedProperty
     * @param optionItem
     */
    public void deleteOptionItem(IFProperty selectedProperty, PropertyOptionItem optionItem) {
        if(propertyService.deleteOptionItemProperty(optionItem)){
            selectedProperty.setPropertyOptionItems(propertyService.findPropertyOptionItem(selectedProperty.getPropertyId()));
        }else {
            //Mostrar Mensaje
        }

    }

    /**
     * Busca todos los <tt>OptionItem</tt> de una propiedad
     * @param propertyId identificador de la propiedad a buscar los <tt>OptionItem</tt>
     * @return <tt>Lis</tt> de <tt>OptionItem</tt>. Si no existe <tt>OptionItem</tt> vinculados a la propiedad,
     *         la <tt>List</tt> es vacia
     */
    public List<PropertyOptionItem> getAllPropertyOptionItem(Integer propertyId) {
        return propertyService.findPropertyOptionItem(propertyId);
    }

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

}
