package com.amsystem.bifaces.util.i18n.case2;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Title: MessageTranslationPK.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 17/10/2016.
 */

@IdClass(MessageTranslationPK.class)
public class MessageTranslationPK implements Serializable {

    @NotEmpty
    @Column(name = "ID_MT", nullable = false)
    private String key;

    @NotEmpty
    @Column(name = "COD_MC", nullable = false)
    private String category;

    @NotEmpty
    @Column(name="LOCALE", nullable=false)
    private String locale;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
