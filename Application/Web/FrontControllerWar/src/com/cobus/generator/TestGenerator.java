package com.cobus.generator;

import com.cobus.dynamictemplate.setting.model.DynamicObject;
import com.cobus.dynamictemplate.setting.services.IDynamicObjectService;
import com.cobus.util.PageMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.List;

/**
 * Title: TestGenerator.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 09/08/2016.
 */
public class TestGenerator implements Serializable {
    private Integer templateId;
    private String templateName;
    private DataTemplate dataTemplate;
    private IDynamicObjectService dynamicObjectBo;

    private static final Logger log = LogManager.getLogger(TestGenerator.class.getName());

    public TestGenerator() {
    }

    public IDynamicObjectService getDynamicObjectBo() {
        return dynamicObjectBo;
    }

    public void setDynamicObjectBo(IDynamicObjectService dynamicObjectBo) {
        this.dynamicObjectBo = dynamicObjectBo;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public DataTemplate getDataTemplate() {
        return dataTemplate;
    }

    public void setDataTemplate(DataTemplate dataTemplate) {
        this.dataTemplate = dataTemplate;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public List<DynamicObject> getAllTemplate(){
        return dynamicObjectBo.loadAllTemplate();
    }

    public void onTemplateChange(){
        log.debug("templateName : " + templateName);
        if(templateName != null){
            DynamicObject loadTemplate = dynamicObjectBo.loadTemplate(null, templateName);
            dataTemplate = new DataTemplate(loadTemplate, PageMode.CREATE);

        }else dataTemplate = null;
    }


    public String getOnFlowProcess() {
        return null;
    }
}
