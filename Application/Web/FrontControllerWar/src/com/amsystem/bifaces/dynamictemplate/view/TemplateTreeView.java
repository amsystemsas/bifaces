package com.amsystem.bifaces.dynamictemplate.view;

import com.amsystem.bifaces.dynamictemplate.controller.TreeOperation;
import com.amsystem.bifaces.util.NodeType;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Title: TemplateTreeView.java
 *
 * @author jaguilar (JAR) File Creation on 24/04/2016
 */

@ManagedBean(name = "templateTreeView")
@ViewScoped
public class TemplateTreeView implements Serializable {

    private static final long serialVersionUID = 1L;

    //Raiz del arbol de plantillas
    private TreeNode root;
    //Nodo seleccionado en el arbol de plantillas
    private TreeNode selectedNode;
    //Nombre de la plantilla a ser registrada
    private String templateName;

    @ManagedProperty("#{propertySectionView}")
    //Seccion de tabla de propiedades
    private PropertySectionView propertySectionView;

    @ManagedProperty("#{nodeVO}")
    //Nodo arbol de plantillas
    private PropertyNode propertyNode;

    @ManagedProperty("#{treeOperation}")
    //Operaciones arbol de plantillas
    private TreeOperation service;

    @PostConstruct
    public void init() {
        root = service.create();
    }


    /**
     * Agrega una nueva plantilla al arbol
     */
    public void addTemplate() {
        service.addTemplate(selectedNode, templateName);
        setTemplateName(null);
    }

    /**
     * Elimina un plantilla del arbol. Si la plantilla tiene almenos una propiedad asociada, se invoca el metodo <tt>confirmationDeleteTemplate</tt>
     */
    public void deleteTemplate() {
        service.deleteTemplate(selectedNode, false);
        // getPropertyNode().setName(null);
    }

    /**
     * Elimina un plantilla del arbol con propiedades asociadas
     */
    public void confirmationDeleteTemplate() {
        service.deleteTemplate(selectedNode, true);
        // getPropertyNode().setName(null);
    }

    public void associatePropertyTemplate() {

        if (propertySectionView.getSelectedProp() != null && selectedNode != null) {
            final PropertyNode dataNode = (PropertyNode) selectedNode.getData();
            if (dataNode.getNodeType() == NodeType.TEMPLATE_NAME) {
                service.addPropertyToTemplate(selectedNode, propertySectionView.getSelectedProp());
            } else {
                showMessage();
            }
        } else {
            showMessage();
        }

    }

    public void disassociatePropertyTemplate() {

        if (selectedNode != null) {
            final PropertyNode dataNode = (PropertyNode) selectedNode.getData();
            if (dataNode.getNodeType() == NodeType.PROPERTY) {
                service.removeChild(selectedNode, true);

            } else {
                showMessage();
            }
        } else {
            showMessage();
        }

    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public PropertyNode getPropertyNode() {
        return propertyNode;
    }

    public void setPropertyNode(PropertyNode propertyNode) {
        this.propertyNode = propertyNode;
    }

    public TreeOperation getService() {
        return service;
    }

    public void setService(TreeOperation service) {
        this.service = service;
    }

    public PropertySectionView getPropertySectionView() {
        return propertySectionView;
    }

    public void setPropertySectionView(PropertySectionView propertySectionView) {
        this.propertySectionView = propertySectionView;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void showMessage() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
    }


}
