package com.cobus.dynamictemplate.setting.bo;

/**
 * Title: PropertyBo.java
 * @author jaguilar (JAR)
 * File Creation on 16/05/2016
 */
public class PropertyTree {
   
    private Integer id;
    private String name;

    public PropertyTree() {
    }

    public PropertyTree(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
   

}
