package com.amsystem.bifaces.configuration;

import com.amsystem.bifaces.user.converter.RoleToUserProfileConverter;
import com.amsystem.bifaces.UserInfo;
import com.amsystem.bifaces.util.i18n.TextBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.ResourceBundle;

/**
 * Title: AppConfig.java <br>
 * Clase que permite las configuraciones iniciales del aplicativo empleando las herramientas que nos proporciona
 * el Framework Spring
 *
 *
 * @author Jaime Aguilar (JAR)
 * File Creation on 07/09/2016.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.amsystem.bifaces")
public class AppWebConfig extends WebMvcConfigurerAdapter {

	@Autowired
    RoleToUserProfileConverter roleToUserProfileConverter;
	

	 /**
     * Configure ViewResolvers to deliver preferred views.
     *
     * @param registry
     */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/views/");
        viewResolver.setSuffix(".xhtml");
        registry.viewResolver(viewResolver);
    }

    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }


    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
    @Bean
    public ResourceBundle msj() {
        UserInfo.initUserDefault();
        TextBundle messageSource = new TextBundle("GENERIC");
        return messageSource;
      /*
      ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
      resource.setBasename("classpath:messages");
      resource.setDefaultEncoding("UTF-8");
      return resource;
      */
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }
    
    /**Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
     * This is a workaround for this issue.

    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
     */
}



