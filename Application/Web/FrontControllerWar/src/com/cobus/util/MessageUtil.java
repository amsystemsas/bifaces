package com.cobus.util;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Title: MessageUtil.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 12/01/2017.
 */

public class MessageUtil {

    public static void showMessage(String s, ErrorType error) {
        switch (error.getValue()) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, s));
                break;
            case 1:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, s));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, s));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, null, s));
                break;
        }
    }

    public static void showModalMessage(String msj, ErrorType error) {
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


}
