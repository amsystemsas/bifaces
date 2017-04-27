package com.cobus.dynamictemplate.vo;

import com.cobus.util.NodeType;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Title: NodeVO.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */

@ManagedBean(name = "nodeVO")
@ViewScoped
public class NodeVO implements Serializable{
    
    private NodeType nodeType;
    private Integer id;
    private Integer category;
    private Integer status;
    private String name;
    
    public NodeVO() {
    }

    public NodeVO(NodeType nodeType, Integer category, Integer status, String name) {
        this.nodeType = nodeType;
        this.category = category;
        this.status = status;
        this.name = name;
    }
    
    public NodeVO(NodeType nodeType, Integer category, Integer status, String name, Integer id) {
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
