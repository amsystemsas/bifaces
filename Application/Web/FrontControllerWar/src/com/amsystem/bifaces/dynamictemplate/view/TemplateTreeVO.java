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
 * Title: TemplateTreeVO.java
 *
 * @author jaguilar (JAR) File Creation on 24/04/2016
 */

@ManagedBean(name = "templateTreeVO")
@ViewScoped
public class TemplateTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private TreeNode root;

    private TreeNode selectedNode;

    private String templateName;

    @ManagedProperty("#{propertySectionView}")
    private PropertySectionView propertySectionView;

    @ManagedProperty("#{nodeVO}")
    private PropertyNode propertyNode;

    @ManagedProperty("#{treeOperation}")
    private TreeOperation treeOperation;

    @PostConstruct
    public void init() {
        root = treeOperation.create();
    }

    //add a new property data into database
    public void addTemplate() {

        if (selectedNode != null) {
                PropertyNode newTemplate = new PropertyNode(templateName);

                treeOperation.addTemplate(selectedNode, newTemplate);
            setTemplateName(null);
        } else {
            showMessage();
            //getPropertyNode().setName(null);
        }

    }


    public void deleteTemplate() {

        if (selectedNode != null) {
            treeOperation.deleteTemplate(selectedNode);
            getPropertyNode().setName(null);
        } else {
            showMessage();
            getPropertyNode().setName(null);
        }

    }

    public void associatePropertyTemplate() {

        if (propertySectionView.getSelectedProp() != null && selectedNode != null) {
            final PropertyNode dataNode = (PropertyNode) selectedNode.getData();
            if (dataNode.getNodeType() == NodeType.TEMPLATE_NAME) {
                treeOperation.addPropertyToTemplate(selectedNode, propertySectionView.getSelectedProp());
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
                treeOperation.removeChild(selectedNode, true);

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

    public TreeOperation getTreeOperation() {
        return treeOperation;
    }

    public void setTreeOperation(TreeOperation treeOperation) {
        this.treeOperation = treeOperation;
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
