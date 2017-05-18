package com.amsystem.bifaces.configuration;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;

/**
 * Clase encargada de cargar las clases de configuraciones del aplicativo
 *
 * Title: AppInitializer.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 07/09/2016.
 */
//public class AppInitializer  {
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppRootConfig.class, PrincipalConf.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new MultipartFilter()};
    }

    /*
    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
        super.registerDispatcherServlet(servletContext);
    }
    */

    @Override
    public void onStartup(ServletContext container) {

        container.setInitParameter("javax.faces.PROJECT_STAGE", "Development");

        container.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springsecurity.taglib.xml");

        container.setInitParameter("primefaces.FONT_AWESOME", "true");

        container.setInitParameter("primefaces.THEME", "start");

        container.setInitParameter("facelets.SKIP_COMMENTS", "true");

/*
        container.addFilter("Spring OpenEntityManagerInViewFilter", org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter.class)
                .addMappingForUrlPatterns(null, false, "/");

        container.addFilter("HttpMethodFilter", org.springframework.web.filter.HiddenHttpMethodFilter.class)
                .addMappingForUrlPatterns(null, false, "/");
*/

        //Esto es solo para WEBLOGIC. Se debe comentar en caso de usar otro servidor Web como GLASSFISH
        container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
/*
        FilterRegistration charEncodingFilterReg = container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
		charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
		charEncodingFilterReg.setInitParameter("forceEncoding", "true");
		charEncodingFilterReg.addMappingForUrlPatterns(null, false, "/");
*/
        super.registerDispatcherServlet(container);

        super.registerContextLoaderListener(container);


    }
    /*

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement( LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }

    private static final String LOCATION = "C:/temp/"; // Temporary location where files will be stored

    private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
    // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.

    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
    */
}


