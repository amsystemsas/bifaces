package com.amsystem.bifaces.configuration;

import com.amsystem.bifaces.security.SecurityConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Title: AppRootConfig.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 23/03/2017.
 */

@Configuration
@ComponentScan({"com.amsystem.bifaces"})
@Import(value = {SecurityConfiguration.class, HibernateConfiguration.class})
public class AppRootConfig {
    // application domain @Beans here...
}
