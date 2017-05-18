package com.amsystem.bifaces.dynamictemplate.controller;


import com.amsystem.bifaces.dynamictemplate.setting.model.IFProperty;
import com.amsystem.bifaces.dynamictemplate.setting.services.PropertyTemplateService;
import com.amsystem.bifaces.util.CategoryName;
import com.amsystem.bifaces.util.MessageUtil;
import com.amsystem.bifaces.util.NodeType;
import com.amsystem.bifaces.dynamictemplate.setting.bo.PropertyTree;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyTemplate;
import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.dynamictemplate.setting.services.TemplateService;
import com.amsystem.bifaces.dynamictemplate.util.TemplateStatus;
import com.amsystem.bifaces.dynamictemplate.view.PropertyNode;
import com.amsystem.bifaces.util.NotificationType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.*;

/**
 * Title: TreeOperation.java
 *
 * @author jaguilar (JAR)
 *         File Creation on 14/05/2016
 */

@Controller
@ViewScoped
@ManagedBean(name = "treeOperation")
public class TreeOperation implements Serializable {

    private static final long serialVersionUID = 7553475076290054162L;

    private static final Logger log = LogManager.getLogger(TreeOperation.class.getName());

    @Autowired
    private TemplateService templateService;

    @Autowired
    private PropertyTemplateService propertyTemplateService;

    @Autowired
    private ResourceBundle rb;

    public TemplateService getTemplateService() {
        return templateService;
    }

    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }

    public PropertyTemplateService getPropertyTemplateService() {
        return propertyTemplateService;
    }

    public void setPropertyTemplateService(PropertyTemplateService propertyTemplateService) {
        this.propertyTemplateService = propertyTemplateService;
    }

    public TreeNode create() {
        log.debug("**Inicio Arbol **");
        TreeNode root = new DefaultTreeNode(new PropertyNode(NodeType.ROOT, CategoryName.ROOT.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.ROOT.getLabel()), null);
        TreeNode product = new DefaultTreeNode(new PropertyNode(NodeType.ROOT, CategoryName.PRODUCT.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.PRODUCT.getLabel()), root);
        TreeNode policy = new DefaultTreeNode(new PropertyNode(NodeType.ROOT, CategoryName.POLICY.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.POLICY.getLabel()), root);
        TreeNode riskUnit = new DefaultTreeNode(new PropertyNode(NodeType.ROOT, CategoryName.RISK_UNIT.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.RISK_UNIT.getLabel()), root);
        TreeNode insuranceObject = new DefaultTreeNode(new PropertyNode(NodeType.ROOT, CategoryName.INSURANCE_OBJECT.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.INSURANCE_OBJECT.getLabel()), root);
        TreeNode coverage = new DefaultTreeNode(new PropertyNode(NodeType.ROOT, CategoryName.COVERAGE.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.COVERAGE.getLabel()), root);
        TreeNode client = new DefaultTreeNode(new PropertyNode(NodeType.ROOT, CategoryName.PARTICIPATION.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.PARTICIPATION.getLabel()), root);
        TreeNode summary = new DefaultTreeNode(new PropertyNode(NodeType.ROOT, CategoryName.SUMMARY.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.SUMMARY.getLabel()), root);
        TreeNode generic = new DefaultTreeNode(new PropertyNode(NodeType.ROOT, CategoryName.GENERIC.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.GENERIC.getLabel()), root);


        HashMap<Template, List<PropertyTree>> dynamicObjList = templateService.findFullTemplateProperty();

        TreeNode childNode;
        PropertyNode tvo;
        Integer category;


        Iterator<Map.Entry<Template, List<PropertyTree>>> templateIterator = dynamicObjList.entrySet().iterator();

        while (templateIterator.hasNext()) {
            Map.Entry<Template, List<PropertyTree>> next = templateIterator.next();
            Template template = next.getKey();

            log.debug("Template: " + template.toString());

            tvo = new PropertyNode(NodeType.TEMPLATE_NAME, template.getCategoryId(), template.getStatus(), template.getName(), template.getTemplateId());
            category = template.getCategoryId();

            switch (category) {

                case 1:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, product);
                    loadChild(childNode, next.getValue());
                    break;
                case 2:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, policy);
                    loadChild(childNode, next.getValue());
                    break;
                case 3:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, riskUnit);
                    loadChild(childNode, next.getValue());
                    break;
                case 4:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, insuranceObject);
                    loadChild(childNode, next.getValue());
                    break;
                case 5:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, coverage);
                    loadChild(childNode, next.getValue());
                    break;
                case 6:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, client);
                    loadChild(childNode, next.getValue());
                    break;
                case 7:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, summary);
                    loadChild(childNode, next.getValue());
                    break;
                case 8:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, generic);
                    loadChild(childNode, next.getValue());
                    break;

            }
        }

        orderRootChild(root);
        return root;
    }

    /**
     * Agrega una nueva plantilla al sistema
     *
     * @param selectedNode Nodo seleccionado al que se le vinculara la nueva plantilla
     * @param templateName Nombre de la plantilla
     */
    public void addTemplate(TreeNode selectedNode, String templateName) {

        if (selectedNode != null) {
            final PropertyNode dataNode = (PropertyNode) selectedNode.getData();
            log.debug("nodeName = " + dataNode.getName() + "\tNodeCategory = " + dataNode.getCategory());
            Template template = new Template(templateName, dataNode.getCategory());

            if (templateService.addTemplate(template)) {
                final PropertyNode childNode = new PropertyNode(NodeType.TEMPLATE_NAME, dataNode.getCategory(), TemplateStatus.ACTIVE.getValue(), templateName);
                addChild(selectedNode, childNode, Boolean.FALSE);
                MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), rb.getString("template_save_success_TT"));
            } else {
                MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), rb.getString("template_duplicate_TT"));
            }

        } else {
            //Mensaje que debe seleeciona un nodo del arbol
            MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), rb.getString("template_validation_category_TT"));
        }

    }

    /**
     * Elimina un plantilla del sistema
     *
     * @param selectedNode Representa la plantilla seleccionada
     * @param confirmation Confirmacion por el usuario de que la plantilla a eliminar tiene propiedades asociadas
     */
    public void deleteTemplate(TreeNode selectedNode, boolean confirmation) {
        log.debug("**** Eliminando plantilla...");
        if (selectedNode != null) {
            //Validar si la plantilla tiene propiedades asociadas
            if (selectedNode.getChildren().isEmpty() || confirmation) {
                final PropertyNode dataNode = (PropertyNode) selectedNode.getData();
                log.debug("nodeName = " + dataNode.getName() + "\tNodeType = " + dataNode.getNodeType().getLabel());
                log.debug("IdTemplate : " + dataNode.getId() + "\tCategory = " + dataNode.getCategory());
                Template template = new Template(dataNode.getName(), dataNode.getCategory());

                if (templateService.deleteTemplate(template)) {
                    removeChild(selectedNode, Boolean.FALSE);
                    MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), rb.getString("template_deleted_success_TT"));
                } else {
                    //Lanzar mensaje que la plantilla tiene datos del negocio
                    //Si la respuesta es afirmativa, llamar metodo de eliminar plantillas
                    MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), rb.getString("template_business_records_TT"));
                }

            } else {
                //Lanzar mensaje de confirmacion para continuar con el proceso de eliminacion
                //Si la respuesta es afirmativa, llamar metodo de eliminar plantillas
                MessageUtil.updateExecute(null, "PF('confirmDeleteTemplDlg').show();");
            }

        } else {
            //Mensaje que debe seleecionar un nodo valido (PROP) del arbol
            MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), rb.getString("template_validation_category_TT"));
        }
    }


    public void addPropertyToTemplate(TreeNode selectedNode, IFProperty selectedProp) {
        log.debug("asociando propiedad a plantilla");

        if (selectedNode != null) {
            PropertyNode propertyNode = new PropertyNode(selectedProp.getPropertyId(), selectedProp.getName(), NodeType.PROPERTY);
            PropertyNode templateNode = (PropertyNode) selectedNode.getData();
            PropertyTemplate propertyTemplate = new PropertyTemplate(selectedProp.getPropertyId(), templateNode.getId(), new Date());
            propertyTemplateService.addPropertyToTemplate(propertyTemplate);
            addChild(selectedNode, propertyNode, Boolean.TRUE);

        }


    }

    public void addChild(TreeNode rootName, PropertyNode childNode, Boolean isLeaf) {

        List<TreeNode> children = rootName.getChildren();
        log.debug(" JRA -> Hijos, antes de: " + children.size());
        TreeNode child;
        if (isLeaf) {
            child = new DefaultTreeNode(NodeType.PROPERTY.getLabel(), childNode, rootName);
            child.setType(NodeType.PROPERTY.getLabel());
        } else {
            child = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), childNode, rootName);
            child.setType(NodeType.TEMPLATE_NAME.getLabel());
        }

        children = rootName.getChildren();
        Collections.sort(children, new LevelComparator());

    }


    public void removeChild(TreeNode selectedNode, boolean onlyChild) {
        if (selectedNode.isLeaf()) {
            log.debug("isLeaf= TRUE");
            TreeNode parent = selectedNode.getParent();
            if (onlyChild) {
                PropertyNode dataNodeChild = (PropertyNode) selectedNode.getData();
                PropertyNode dataNodeParent = (PropertyNode) parent.getData();
                log.debug("dataNodeChild : " + dataNodeChild + "\tName : " + dataNodeChild.getName());
                log.debug("dataNodeParent : " + dataNodeParent + "\tName : " + dataNodeParent.getName());
                PropertyTemplate propertyTemplate = new PropertyTemplate(dataNodeChild.getId(), dataNodeParent.getId());
                propertyTemplateService.deletePropertyToTemplate(propertyTemplate);
            }
            parent.getChildren().remove(selectedNode);

        } else {
            log.debug("isLeaf= FALSE");
            TreeNode parent = selectedNode.getParent();
            parent.getChildren().remove(selectedNode);
        }
    }

    private void orderRootChild(TreeNode root) {
        final List<TreeNode> children = root.getChildren();
        log.debug("Hijos de Root : " + children.size());
        for (TreeNode node : children) {
            if (node.getChildCount() > 0)
                Collections.sort(node.getChildren(), new LevelComparator());

        }

    }

    private void loadChild(TreeNode root, List<PropertyTree> propertyList) {
        log.debug("Padre: " + ((PropertyNode) root.getData()).getName() + "\tHijos: " + propertyList.size());
        for (PropertyTree prop : propertyList) {
            final PropertyNode childNode = new PropertyNode(NodeType.PROPERTY, null, null, prop.getName(), prop.getId());
            addChild(root, childNode, Boolean.TRUE);

        }
    }


    class LevelComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            PropertyNode u1 = (PropertyNode) ((TreeNode) o1).getData();
            PropertyNode u2 = (PropertyNode) ((TreeNode) o2).getData();
            int res = String.CASE_INSENSITIVE_ORDER.compare(u1.getName(), u2.getName());
            if (res == 0) {
                res = u1.getName().compareTo(u2.getName());
            }
            return res;

        }

    }

}
