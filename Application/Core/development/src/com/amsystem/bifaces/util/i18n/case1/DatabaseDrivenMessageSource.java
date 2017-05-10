package com.amsystem.bifaces.util.i18n.case1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Title: DatabaseDrivenMessageSource.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/03/2017.
 */

public class DatabaseDrivenMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final Logger log = LogManager.getLogger(DatabaseDrivenMessageSource.class.getName());

    private ResourceLoader resourceLoader;

    private static Map properties = new HashMap<>();

    @Autowired
    private DataSource dataSource;

    private static JdbcTemplate jdbcTemplate;


    public DatabaseDrivenMessageSource() {
        reload();
    }

    /*
    public DatabaseDrivenMessageSource(MessageResourceService messageResourceService) {
        this.messageResourceService = messageResourceService;
        reload();
    }
    */

    @PostConstruct
    public void init() {
        System.out.println("[Text] Initializing message source using query [{}]: " + getDataSource());
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(getDataSource());
        }

        System.out.println("[Text] Initializing message : " + jdbcTemplate);
        //current_locale = UserInfo.getLocaleUser();
        //setParent(ResourceBundle.getBundle(BASE_NAME, current_locale, DB_CONTROL));
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getText(code, locale);
        MessageFormat result = createMessageFormat(msg, locale);
        return result;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return getText(code, locale);
    }

    private String getText(String code, Locale locale) {
        Map<String, String> localized = (Map<String, String>) properties.get(code);
        String textForCurrentLanguage = null;
        if (localized != null) {
            textForCurrentLanguage = localized.get(locale.getLanguage());
            if (textForCurrentLanguage ==  null) {
                textForCurrentLanguage = localized.get(Locale.FRANCE.getLanguage());
            }
        }
        if (textForCurrentLanguage == null) {
            //Check parent message
            logger.debug("Fallback to properties message");
            try {
                textForCurrentLanguage = getParentMessageSource().getMessage(code, null, locale);
            } catch (Exception e) {
                logger.error("Cannot find message with code: " + code);
            }
        }
        return textForCurrentLanguage != null ? textForCurrentLanguage : code;
    }

    public void reload() {
        properties.clear();
        properties.putAll(loadTexts());
    }

    protected Map<String, Map<String, String>> loadTexts() {
        log.debug("loadTexts");
        Map<String, Map<String, String>> m = new HashMap<String, Map<String, String>>();
        /*
        List<MessageResource> texts = messageResourceService.loadAllMessages();
        for (MessageResource text : texts) {
            Map<String, String> v = new HashMap<String, String>();
            v.put("en", text.getEnglish());
            v.put("de", text.getGerman());
            v.put("fr", text.getFrench());
            v.put("en_US", text.getAmerican());
            m.put(text.getMessageKey(), v);
        }
        */
        return m;

    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
    }
}
