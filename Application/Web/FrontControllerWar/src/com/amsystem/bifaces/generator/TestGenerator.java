package com.amsystem.bifaces.generator;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.dynamictemplate.setting.services.TemplateService;
import com.amsystem.bifaces.util.PageMode;
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
    private TemplateService templateService;

    private static final Logger log = LogManager.getLogger(TestGenerator.class.getName());

    public TestGenerator() {
    }

    public TemplateService getTemplateService() {
        return templateService;
    }

    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
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

    public List<Template> getAllTemplate(){
        return templateService.findAllTemplate();
    }

    public void onTemplateChange(){
        log.debug("templateName : " + templateName);
        if(templateName != null){
            Template loadTemplate = templateService.findTemplateByName(templateName);
            dataTemplate = new DataTemplate(loadTemplate, PageMode.CREATE);

        }else dataTemplate = null;
    }


    public String getOnFlowProcess() {
        return null;
    }
}
