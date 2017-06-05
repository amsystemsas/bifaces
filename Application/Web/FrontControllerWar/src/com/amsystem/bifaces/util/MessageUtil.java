package com.amsystem.bifaces.util;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Title: MessageUtil.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 12/01/2017.
 */
@Controller
@ViewScoped
public class MessageUtil {

    @Autowired
    private ResourceBundle rb;

    public static void showMessage(NotificationType error, String title, String message) {
        switch (error.getValue()) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtil.isEmpty(title)? NotificationType.INFO.getLabel() : title, message));
                break;
            case 1:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, StringUtil.isEmpty(title)? NotificationType.WARN.getLabel() : title, message));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtil.isEmpty(title)? NotificationType.ERROR.getLabel() : title, message));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, StringUtil.isEmpty(title)? NotificationType.FATAL.getLabel() : title, message));
                break;
        }
    }

    public static void showModalMessage(NotificationType error, String msj) {
        FacesMessage message = null;
        switch (error.getValue()) {
            case 0:
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", msj);
                break;
            case 1:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", msj);
                break;
            case 2:
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msj);
                break;
            case 3:
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", msj);
                break;
        }

        RequestContext.getCurrentInstance().showMessageInDialog(message);

    }

    /**
     *
     * @param componentUpdateName
     * @param componentExecuteName
     */
    public static void updateExecute(List<String> componentUpdateName, String componentExecuteName) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(componentUpdateName != null && !componentUpdateName.isEmpty())  requestContext.update(componentUpdateName);
        if(!StringUtil.isEmptyOrNullValue(componentExecuteName))  requestContext.execute(componentExecuteName);
    }


}
