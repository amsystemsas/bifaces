package com.amsystem.bifaces.util;

import org.primefaces.context.RequestContext;

/**
 * Title: ComponentOperation.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 12/01/2017.
 */

public class ComponentOperation {

    public static void updateComponent(String componentName) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update(componentName);
    }

    public static void executeComponent(String componentName) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute(componentName);
    }

}
