package com.amsystem.bifaces.util.i18n.case2;

/**
 * Title: GenericMessageTranslation.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 17/10/2016.
 */
public interface GenericMessageTranslation {

    void setMessage(String message);

    String getMessage();

    String getKey();

    String getCategory();

    String getLocale();


}
