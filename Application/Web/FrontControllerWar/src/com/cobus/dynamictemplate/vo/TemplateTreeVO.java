package com.cobus.dynamictemplate.vo;

import com.cobus.dynamictemplate.controller.TreeOperation;
import com.cobus.util.NodeType;
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

    @ManagedProperty("#{propertySectionVO}")
    private PropertySectionVO propertySectionVO;

    @ManagedProperty("#{nodeVO}")
    private NodeVO nodeVO;

    @ManagedProperty("#{treeOperation}")
    private TreeOperation treeOperation;

    public TemplateTreeVO() {
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

    public NodeVO getNodeVO() {
        return nodeVO;
    }

    public void setNodeVO(NodeVO nodeVO) {
        this.nodeVO = nodeVO;
    }

    public TreeOperation getTreeOperation() {
        return treeOperation;
    }

    public void setTreeOperation(TreeOperation treeOperation) {
        this.treeOperation = treeOperation;
    }

    public PropertySectionVO getPropertySectionVO() {
        return propertySectionVO;
    }

    public void setPropertySectionVO(PropertySectionVO propertySectionVO) {
        this.propertySectionVO = propertySectionVO;
    }

    @PostConstruct
    public void init() {
        root = treeOperation.create();
    }

    //add a new property data into database
    public void addTemplate() {

        if (selectedNode != null) {
                treeOperation.addTemplate(selectedNode, getNodeVO());
            getNodeVO().setName(null);
        } else {
            showMessage();
            getNodeVO().setName(null);
        }

    }


    public void deleteTemplate() {

        if (selectedNode != null) {
            treeOperation.deleteTemplate(selectedNode);
            getNodeVO().setName(null);
        } else {
            showMessage();
            getNodeVO().setName(null);
        }

    }

    public void associatePropertyTemplate() {

        if (propertySectionVO.getSelectedProp() != null && selectedNode != null) {
            final NodeVO dataNode = (NodeVO) selectedNode.getData();
            if (dataNode.getNodeType() == NodeType.TEMPLATE_NAME) {
                treeOperation.addPropertyToTemplate(selectedNode, propertySectionVO.getSelectedProp());
            } else {
                showMessage();
            }
        } else {
            showMessage();
        }

    }

    public void disassociatePropertyTemplate() {

        if (selectedNode != null) {
            final NodeVO dataNode = (NodeVO) selectedNode.getData();
            if (dataNode.getNodeType() == NodeType.PROPERTY) {
                treeOperation.removeChild(selectedNode, true);

            } else {
                showMessage();
            }
        } else {
            showMessage();
        }

    }


    public void showMessage() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
    }


}
