package com.amsystem.bifaces.util;

import com.amsystem.bifaces.Country;
import com.amsystem.bifaces.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Title: LocaleBean.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/12/2016.
 */



@ManagedBean(name = "language")
@ViewScoped
public class LocaleBean implements Serializable {

    private String selectedLocale;

    private static Map<String, String> locale;

    private static final long serialVersionUID = 1L;

    private static final Logger log = LogManager.getLogger(LocaleBean.class.getName());


    @PostConstruct
    public void init() {
        locale = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : Country.getLanguage().entrySet()) {
            locale.put(entry.getKey(), ((Locale) entry.getValue()).getLanguage());
        }

    }

    public String getSelectedLocale() {
        return selectedLocale;
    }

    public void setSelectedLocale(String selectedLocale) {
        this.selectedLocale = selectedLocale;
    }

    public Map<String, String> getLocaleInMap() {
        return locale;
    }

    public static void setLocale(Map<String, String> locale) {
        LocaleBean.locale = locale;
    }

    //value change event listener
    public void onLanguageChange() {
        log.debug("Selected Locale = " + selectedLocale);

        //loop country map to compare the locale code
        for (Map.Entry<String, String> entry : locale.entrySet()) {

            if (entry.getValue().toString().equals(selectedLocale)) {
                Locale newLocale = new Locale(entry.getValue());
                UserInfo.setLocaleUser(newLocale);
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale(newLocale);

            }
        }
    }
}
