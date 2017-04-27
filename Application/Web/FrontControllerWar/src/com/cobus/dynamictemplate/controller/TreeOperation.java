package com.cobus.dynamictemplate.controller;


import com.cobus.dynamictemplate.setting.bo.PropertyTree;
import com.cobus.dynamictemplate.setting.model.DynamicObject;
import com.cobus.dynamictemplate.setting.services.IDynamicObjectService;
import com.cobus.dynamictemplate.setting.services.IPropertyTemplateService;
import com.cobus.dynamictemplate.util.TemplateStatus;
import com.cobus.dynamictemplate.vo.NodeVO;
import com.cobus.dynamictemplate.vo.PropertyVO;
import com.cobus.util.CategoryName;
import com.cobus.util.NodeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * @author jaguilar (JAR)
 * File Creation on 14/05/2016
 */

@Controller
@ViewScoped
@ManagedBean(name = "treeOperation")
public class TreeOperation implements Serializable{

    private static final long serialVersionUID = 7553475076290054162L;

    private static final Logger log = LogManager.getLogger(TreeOperation.class.getName());

    @Autowired
    private IDynamicObjectService dynamicObjectService;

    @Autowired
    private IPropertyTemplateService propertyTemplateService;

    public IDynamicObjectService getDynamicObjectService() {
        return dynamicObjectService;
    }

    public void setDynamicObjectService(IDynamicObjectService dynamicObjectService) {
        this.dynamicObjectService = dynamicObjectService;
    }

    public IPropertyTemplateService getPropertyTemplateService() {
        return propertyTemplateService;
    }

    public void setPropertyTemplateService(IPropertyTemplateService propertyTemplateService) {
        this.propertyTemplateService = propertyTemplateService;
    }

    public TreeNode create() {
        log.debug("**Inicio Arbol **");
        TreeNode root = new DefaultTreeNode(new NodeVO(NodeType.ROOT, CategoryName.ROOT.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.ROOT.getLabel()), null);
        TreeNode generic = new DefaultTreeNode(new NodeVO(NodeType.ROOT, CategoryName.GENERIC.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.GENERIC.getLabel()), root);
        TreeNode product = new DefaultTreeNode(new NodeVO(NodeType.ROOT, CategoryName.PRODUCT.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.PRODUCT.getLabel()), root);
        TreeNode other = new DefaultTreeNode(new NodeVO(NodeType.ROOT, CategoryName.OTHER.getValue(), TemplateStatus.ACTIVE.getValue(), CategoryName.OTHER.getLabel()), root);

        HashMap<DynamicObject, List<PropertyTree>> dynamicObjList = dynamicObjectService.loadAllTemplateProperty();
        
        TreeNode childNode;
        NodeVO tvo;
        Integer category;


        Iterator<Map.Entry<DynamicObject, List<PropertyTree>>> templateIterator = dynamicObjList.entrySet().iterator();
        
        while (templateIterator.hasNext()){
            Map.Entry<DynamicObject, List<PropertyTree>> next = templateIterator.next();
            DynamicObject dynamicObject = next.getKey();
        
            tvo = new NodeVO(NodeType.TEMPLATE_NAME, dynamicObject.getCategory(), dynamicObject.getStatus(), dynamicObject.getName(), dynamicObject.getDoId());
            category = dynamicObject.getCategory();

            switch (category) {
                case 1:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, generic);
                    loadChild(childNode, next.getValue());
                    break;
                case 2:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, product);
                    loadChild(childNode, next.getValue());
                    break;
                case 3:
                    childNode = new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(), tvo, other);
                    loadChild(childNode, next.getValue());
                    break;
            }
        }
      
        orderRootChild(root);
        return root;
    }

    //add a new template data into database
    public void addTemplate(TreeNode selectedNode, NodeVO nodeVO) {

        if (selectedNode != null) {
            final NodeVO dataNode = (NodeVO) selectedNode.getData();
            log.debug("nodeName = " + dataNode.getName() + "\tNodeCategory = " + dataNode.getCategory());
            DynamicObject template = new DynamicObject();
            template.setName(nodeVO.getName());
            template.setCategory(dataNode.getCategory());
            dynamicObjectService.addTemplate(template);
            final NodeVO childNode = new NodeVO(NodeType.TEMPLATE_NAME, dataNode.getCategory(), TemplateStatus.ACTIVE.getValue(), nodeVO.getName());
            addChild(selectedNode, childNode, Boolean.FALSE);

        } 

    }

    public void addPropertyToTemplate(TreeNode selectedNode, PropertyVO selectedProp) {
        log.debug("asociando propiedad a plantilla");
        if (selectedNode != null) {
            final NodeVO dataNode = (NodeVO) selectedNode.getData();
            log.debug("nodeName = " + dataNode.getName() + "\tNodeCategory = " + dataNode.getCategory());
            Integer propertyId = selectedProp.getPropertyId();
            propertyTemplateService.addPropertyToTemplate(propertyId, dataNode.getId(), dataNode.getName());
            final NodeVO childNode = new NodeVO(NodeType.PROPERTY, null, null, selectedProp.getName(), propertyId);
            addChild(selectedNode, childNode, Boolean.TRUE);

        }

    }

    public void addChild(TreeNode rootName, NodeVO childNode, Boolean isLeaf) {

            List<TreeNode> children = rootName.getChildren();
            log.debug(" JRA -> Hijos, antes de: " + children.size());
            TreeNode child;
            if(isLeaf){
                child = new DefaultTreeNode(NodeType.PROPERTY.getLabel(),childNode, rootName);
            }else{
                child =new DefaultTreeNode(NodeType.TEMPLATE_NAME.getLabel(),childNode, rootName);
            }
            
            children = rootName.getChildren();
            Collections.sort(children, new LevelComparator());

    }
    
    public void deleteTemplate(TreeNode selectedNode) {
        
        if (selectedNode != null) {
            final NodeVO dataNode = (NodeVO) selectedNode.getData();
            log.debug("nodeName = " + dataNode.getName() + "\tNodeType = " + dataNode.getNodeType().getLabel());
            if(dataNode.getNodeType().equals(NodeType.PROPERTY)){
                log.debug("Desasociando Propiedad: " + dataNode.getName());
                //TODO: Validar que la Propiedad no se encuentre asociada a datos del Negocio
                //Validar existencia de datos en la propiedad
            }else{
                
                log.debug("Eliminando Plantilla: " + dataNode.getName());
            }
            
            DynamicObject template = new DynamicObject();
            template.setName(dataNode.getName());
            template.setCategory(dataNode.getCategory());
            dynamicObjectService.deleteTemplate(template);
            removeChild(selectedNode, Boolean.FALSE);
        }
    }
    
     public void removeChild(TreeNode selectedNode, boolean onlyChild) {
        if(selectedNode.isLeaf()){
            log.debug("isLeaf= TRUE");
            TreeNode parent = selectedNode.getParent();
            if(onlyChild) {
                NodeVO dataNodeChild = (NodeVO)selectedNode.getData();
                NodeVO dataNodeParent = (NodeVO)parent.getData();
                log.debug("dataNodeChild : " + dataNodeChild + "\tName : " + dataNodeChild.getName());
                log.debug("dataNodeParent : " + dataNodeParent + "\tName : " + dataNodeParent.getName());
                propertyTemplateService.deletePropertyToTemplate(dataNodeChild.getId(), dataNodeParent.getId(), dataNodeParent.getName());
            }
            parent.getChildren().remove(selectedNode);
            
        }else{
            log.debug("isLeaf= FALSE");
            TreeNode parent = selectedNode.getParent();
            parent.getChildren().remove(selectedNode);
        }
    }

    private void orderRootChild(TreeNode root) {
        final List<TreeNode> children = root.getChildren();
        log.debug("Hijos de Root : " + children.size());
        for(TreeNode node : children){
            if(node.getChildCount()> 0)
                Collections.sort(node.getChildren(), new LevelComparator());
            
        }
        
    }

    private void loadChild(TreeNode root, List<PropertyTree> propertyList) {
        log.debug("Padre: " + ((NodeVO)root.getData()).getName() + "\tHijos: " + propertyList.size());
        for (PropertyTree prop : propertyList){
            final NodeVO childNode = new NodeVO(NodeType.PROPERTY, null, null, prop.getName(), prop.getId());
            addChild(root, childNode, Boolean.TRUE);
            
        }
    }


    class LevelComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            NodeVO u1 = (NodeVO) ((TreeNode) o1).getData();
            NodeVO u2 = (NodeVO) ((TreeNode) o2).getData();
            int res = String.CASE_INSENSITIVE_ORDER.compare(u1.getName(), u2.getName());
            if (res == 0) {
                res = u1.getName().compareTo(u2.getName());
            }
            return res;

        }

    }

}
