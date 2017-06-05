package com.amsystem.bifaces.dynamictemplate.view;

import com.amsystem.bifaces.dynamictemplate.controller.TreeOperation;
import com.amsystem.bifaces.util.CategoryName;
import com.amsystem.bifaces.util.MessageUtil;
import com.amsystem.bifaces.util.NodeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.List;

/**
 * Title: TemplateTreeView.java
 *
 * @author jaguilar (JAR) File Creation on 24/04/2016
 */

@ManagedBean(name = "templateTreeView")
@ViewScoped
public class TemplateTreeView implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributo usado para la impresion de logs
    private static final Logger log = LogManager.getLogger(TemplateTreeView.class.getName());

    //Raiz del arbol de plantillas
    private TreeNode root;

    //Nodo seleccionado en el arbol de plantillas
    private TreeNode selectedNode;

    //Nombre de la plantilla a ser registrada
    private String templateName;

    //Nombre de la plantilla clonada
    private String cloneTemplateName;

    //Categoria destino para la plantilla clonada
    private CategoryName selectedCategory;

    //Lista de categorias disponibles para las plantillas
    private List<SelectItem> categoryNameItems;

    @ManagedProperty("#{propertySectionView}")
    //Seccion de tabla de propiedades
    private PropertySectionView propertySectionView;

    @ManagedProperty("#{nodeVO}")
    //Nodo arbol de plantillas
    private PropertyTemplateNode propertyTemplateNode;

    @ManagedProperty("#{treeOperation}")
    //Operaciones arbol de plantillas
    private TreeOperation service;

    @PostConstruct
    public void init() {
        root = service.create();
    }


    /**
     * Agrega una nueva plantilla al arbol y al sistema
     */
    public void addTemplate() {
        service.addTemplate(selectedNode, templateName);
        setTemplateName(null);
    }

    /**
     * Elimina un plantilla del arbol. Si la plantilla tiene almenos una propiedad asociada, se invoca el metodo
     * <tt>confirmationDeleteTemplate</tt>
     */
    public void deleteTemplate() {
        service.deleteTemplate(selectedNode, false);
    }

    /**
     * Elimina un plantilla del arbol con propiedades asociadas
     */
    public void confirmationDeleteTemplate() {
        service.deleteTemplate(selectedNode, true);
    }

    /**
     * Agrega una propiedad seleccionada a una plantilla del arbol seleccionada
     */
    public void associatePropertyTemplate() {

        if (propertySectionView.getSelectedProp() != null && selectedNode != null) {
            final PropertyTemplateNode dataNode = (PropertyTemplateNode) selectedNode.getData();
            if (dataNode.getNodeType() == NodeType.TEMPLATE_NAME) {
                service.addPropertyToTemplate(selectedNode, propertySectionView.getSelectedProp());
            } else {
                showMessage();
            }
        } else {
            showMessage();
        }

    }

    /**
     * Desvincula una propiedad de una plantilla.
     */
    public void disassociatePropertyTemplate() {

        if (selectedNode != null) {
            final PropertyTemplateNode dataNode = (PropertyTemplateNode) selectedNode.getData();
            if (dataNode.getNodeType() == NodeType.PROPERTY) {
                service.removeChild(selectedNode, true);

            } else {
                showMessage();
            }
        } else {
            showMessage();
        }

    }

    /**
     * Crea o clona una propiedad a partir de otra propiedad seleccionada
     */
    public void cloneTemplate() {
        log.debug(" ************** Clonando Propiedad *************: ");
        log.debug(selectedCategory);
        service.cloneTemplate(getCloneTemplateName(), selectedNode, selectedCategory);
        setCloneTemplateName(null);
        MessageUtil.updateExecute(null, "PF('clonePropertyDlg').hide();");
        init();

    }

    public CategoryName[] getCategoryNames() {
        return CategoryName.values();
    }

    public CategoryName getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryName selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public List<SelectItem> getCategoryNameItems() {
        return categoryNameItems;
    }

    public void setCategoryNameItems(List<SelectItem> categoryNameItems) {
        this.categoryNameItems = categoryNameItems;
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

    public PropertyTemplateNode getPropertyTemplateNode() {
        return propertyTemplateNode;
    }

    public void setPropertyTemplateNode(PropertyTemplateNode propertyTemplateNode) {
        this.propertyTemplateNode = propertyTemplateNode;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getCloneTemplateName() {
        return cloneTemplateName;
    }

    public void setCloneTemplateName(String cloneTemplateName) {
        this.cloneTemplateName = cloneTemplateName;
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

    public void showMessage() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
    }

}
