package com.amsystem.bifaces.dynamictemplate.view;

import com.amsystem.bifaces.dynamictemplate.controller.PropertyOperation;
import com.amsystem.bifaces.dynamictemplate.setting.model.IFProperty;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

/**
 * Clase que maneja las acciones de los componentes referente a una propiedad.
 *
 * Title: PropertySectionView.java
 * @author Jaime Aguilar (JAR)
 * File Creation on 14/05/2016
 */

@ManagedBean(name = "propertySectionView")
@ViewScoped
public class PropertySectionView implements Serializable{

    //Atributo usado para la impresion de logs
    private static final Logger log = LogManager.getLogger(PropertySectionView.class.getName());

    //Nombre de la nueva propiedad a registrar
    private String propertyName;

    //Nombre de la nueva propiedad a registrar
    private String clonePropertyTemplateName;

    //Descripcion de la opcion para una propiedad
    private String optionItemDescription;

    //Valor de la opcion para una propiedad
    private String optionItemValue;

    private PropertyOptionItem optionItemSelected;

    //Lista de propiedades
    private List<IFProperty> properties;

    private String commandClicked;

    //Propiedad actual seleccionada en la tabla
    private IFProperty selectedProp;

    @ManagedProperty("#{propertyOperation}")
    private PropertyOperation service;


    @PostConstruct
    public void init() {
        properties = service.propertyList();
    }


    /**
     *  Agrega una nueva propiedad
     */
    public void addProperty() {
        log.debug("Agregando Propiedad : " + getPropertyName());
        service.addProperty(getPropertyName());
        setPropertyName(null);
        init();
    }

    /**
     * Elimina una propiedad del negocio siempre y cuando la propiedad no se encuentre asociada a una plantilla
     */
    public void deleteProperty(){
        log.debug("Eliminando Propiedad : " + selectedProp.getName());
        service.deleteProperty(selectedProp);
        init();
    }


    /**
     * Actualiza la informacion de una propiedad seleccionada
     */
    public void updateProperty() {
        log.debug(" ************** Actualizando Propiedad *************: " + selectedProp.getName());
        service.updateProperty(selectedProp);

    }

    /**
     * Actualiza la informacion de una propiedad seleccionada
     */
    public void cloneProperty() {
        log.debug(" ************** Clonando Propiedad *************: " + selectedProp.getName());
        service.cloneProperty(getClonePropertyTemplateName(), selectedProp);
        setClonePropertyTemplateName(null);
        init();

    }

    /**
     * Agrega una nueva opciona a una propiedad seleccionada
     */
    public void addOptionItem() {
        PropertyOptionItem item = new PropertyOptionItem(selectedProp.getPropertyId(), Float.valueOf(getOptionItemValue()), getOptionItemDescription(), null);
        log.debug("Agregando Opcion: " + item);
        service.addOptionItem(selectedProp, item);
        setOptionItemDescription(null);
        setOptionItemValue(null);

    }

    /**
     * Elimina un <tt>OptionItem</tt> de una propiedad seleccionada
     */
    public void deleteOptionItem() {
        log.debug("Eliminando OptionItem: " + optionItemSelected);
        service.deleteOptionItem(selectedProp, optionItemSelected);
        setOptionItemSelected(null);

    }

    /**
     * Busca las <i>Opciones</i> de una propiedad seleccionada de tipo <tt>lista</tt>
     * @return <tt>Lista</tt> de opciones de la propiedad
     */
    public List<PropertyOptionItem> getAllPropertyOptionItem(){
        return (selectedProp != null) ? service.getAllPropertyOptionItem(selectedProp.getPropertyId()) : null;
    }

    public void listenerCloneClickedBtn(ActionEvent actionEvent){
        commandClicked = actionEvent.getComponent().getId();
        log.debug("Click :" + commandClicked);

    }


    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getClonePropertyTemplateName() {
        return clonePropertyTemplateName;
    }

    public void setClonePropertyTemplateName(String clonePropertyTemplateName) {
        this.clonePropertyTemplateName = clonePropertyTemplateName;
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

    public List<IFProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<IFProperty> properties) {
        this.properties = properties;
    }

    public IFProperty getSelectedProp() {
        return selectedProp;
    }

    public void setSelectedProp(IFProperty selectedProp) {
        this.selectedProp = selectedProp;
    }

    public PropertyOperation getService() {
        return service;
    }

    public void setService(PropertyOperation service) {
        this.service = service;
    }

    public PropertyOptionItem getOptionItemSelected() {
        return optionItemSelected;
    }

    public void setOptionItemSelected(PropertyOptionItem optionItemSelected) {
        this.optionItemSelected = optionItemSelected;
    }

    public String getCommandClicked() {
        return commandClicked;
    }

    public void setCommandClicked(String commandClicked) {
        this.commandClicked = commandClicked;
    }
}
