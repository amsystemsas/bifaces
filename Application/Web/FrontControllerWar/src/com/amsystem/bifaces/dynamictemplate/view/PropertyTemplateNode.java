package com.amsystem.bifaces.dynamictemplate.view;

import com.amsystem.bifaces.util.NodeType;

import java.io.Serializable;

/**
 * Title: PropertyTemplateNode.java
 * <p>Representa la data del nodo del arbol de plantillas. Segun el {@code NodeType} se determina si el nodo pertenece
 * a una propiedad o a una plantilla</p>
 *
 * @author Jaime Aguilar (JAR)
 *
 * File Creation on 24/04/2016
 */

public class PropertyTemplateNode implements Serializable{
    
    private Integer id;
    private String name;
    private Integer category;
    private Integer status;
    private NodeType nodeType;

    public PropertyTemplateNode(String name) {
        this.name = name;
    }

    public PropertyTemplateNode(Integer id, String name, NodeType nodeType) {
        this.id = id;
        this.name = name;
        this.nodeType = nodeType;
    }

    public PropertyTemplateNode(NodeType nodeType, Integer category, Integer status, String name) {
        this.nodeType = nodeType;
        this.category = category;
        this.status = status;
        this.name = name;
    }
    
    public PropertyTemplateNode(NodeType nodeType, Integer category, Integer status, String name, Integer id) {
        this.nodeType = nodeType;
        this.category = category;
        this.status = status;
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 

}
