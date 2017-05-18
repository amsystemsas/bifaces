package com.amsystem.bifaces.dynamictemplate.setting.model;

/**
 * Title: CategoryTemplate.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 03/05/2017.
 */


public class CategoryTemplate {
    private Integer ctId;
    private String name;
    private String description;

    public CategoryTemplate(Integer ctId, String name, String description) {
        this.ctId = ctId;
        this.name = name;
        this.description = description;
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
