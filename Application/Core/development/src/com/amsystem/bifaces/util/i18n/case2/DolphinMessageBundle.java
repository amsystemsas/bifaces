package com.amsystem.bifaces.util.i18n.case2;

import java.util.*;

/**
 * Title: DolphinMessageBundle.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 17/10/2016.
 */

public class DolphinMessageBundle extends ResourceBundle {

    protected String bundleName;


    public DolphinMessageBundle(String bundleName) {
        this.bundleName = bundleName;
        setParent(ResourceBundle.getBundle(bundleName, Locale.ENGLISH));
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    @Override
    protected Object handleGetObject(String key) {
        return DolphinLabels.getBundleLabel(this.bundleName, key);
    }

    @Override
    public Enumeration<String> getKeys() {
        Map bundlesProperties = DolphinLabels.getBundleLabel();
        Set set = bundlesProperties.keySet();
        Set keys = new TreeSet();
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();

            if (key.indexOf(bundleName)==0) {
                keys.add(DolphinLabels.extractKey(key));
            }
        }
        return Collections.enumeration(keys);
    }
}
