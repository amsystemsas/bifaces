package com.amsystem.bifaces.configuration;

import com.amsystem.bifaces.Country;
import com.amsystem.bifaces.UserInfo;
import com.amsystem.bifaces.util.SymbolType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Title: PrincipalConf.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 10/03/2017.
 */

@Configuration
@PropertySource("classpath:/config/configuration_file.properties")
public class PrincipalConf {

    @Autowired
    private Environment environment;

    @Bean
    public Country userCountry() {

        System.out.println("INICIAL...!!!");

        String country = environment.getRequiredProperty("country");
        String defaultLanguage = environment.getRequiredProperty("default_language");
        String defaultLanguageISO = defaultLanguage.substring(defaultLanguage.indexOf(SymbolType.LEFT_PARENTHESIS.getValue()) + 1, defaultLanguage.length() - 1);
        String[] languageList = environment.getRequiredProperty("language").split(SymbolType.COMMA.getValue());
        Map languagesMap = new LinkedHashMap<>();

        for (String language : languageList) {
            String l = language.substring(0, language.indexOf(SymbolType.LEFT_PARENTHESIS.getValue()));
            String loc = language.substring(language.indexOf(SymbolType.LEFT_PARENTHESIS.getValue()) + 1, language.length() - 1);
            languagesMap.put(l, new Locale(loc));
        }

        Country countryDefault = Country.initCountryDefault(country, defaultLanguage, defaultLanguageISO, languagesMap);

        System.out.println("USer Default = " + UserInfo.getLocaleUser());
        System.out.println("countryDefault = " + countryDefault);

        return countryDefault;

        //DolphinLabels.createSingletonInstance();
        //DolphinLabels.loadCentralBundles();
    }


}
