package com.cobus.dynamictemplate.vo;

import com.cobus.dynamictemplate.setting.model.Property;
import com.cobus.dynamictemplate.util.RenderingType;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Title: PropertyVO.java
 * @author jaguilar (JAR)
 * File Creation on 09/04/2016
 */

@ManagedBean(name = "propertyVO")
@ViewScoped
public class PropertyVO extends Property{

    private String renderingTypeLabel;

    public PropertyVO() {
    }

    public PropertyVO(Integer propertyId, String name, String label, int type, int renderingType,
                      String expressionValidator, String formula, String defaultValue, boolean visible,
                      boolean editable, boolean required, String parent, String mask) {
        super(propertyId, name, label, type, renderingType, expressionValidator, formula, defaultValue,
                visible, editable, required, parent, mask);

        this.renderingTypeLabel = RenderingType.valueOf(renderingType).getLabel();
    }



    public String getRenderingTypeLabel() {
        return renderingTypeLabel;
    }

    public void setRenderingTypeLabel(String renderingTypeLabel) {
        this.renderingTypeLabel = renderingTypeLabel;
    }


}
