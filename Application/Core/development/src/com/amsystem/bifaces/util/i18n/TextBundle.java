package com.amsystem.bifaces.util.i18n;

import com.amsystem.bifaces.UserInfo;
import com.amsystem.bifaces.util.DolphinLocaleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Title: Text.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 18/02/2017.
 */

public class TextBundle extends ResourceBundle {

    protected String BASE_NAME;

    private static final Control DB_CONTROL = new DBControl();

    private Map<String, String> messages;

    @Autowired
    private DataSource dataSource;

    private static JdbcTemplate jdbcTemplate;

    private static Locale current_locale;

    @PostConstruct
    public void init() {
        System.out.println("[Text] Initializing message source using query [{}]: " + getDataSource());
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(getDataSource());
        }

        System.out.println("[Text] Initializing message : " + jdbcTemplate);
        current_locale = UserInfo.getLocaleUser();
        setParent(ResourceBundle.getBundle(BASE_NAME, current_locale, DB_CONTROL));
    }

    public TextBundle(String baseName) {
        this.BASE_NAME = baseName;

    }

    protected TextBundle(Map<String, String> messages) {
        this.messages = messages;
    }

    @Override
    protected Object handleGetObject(String key) {
        return DBControl.getBundleLabel(key);

    }

    @Override
    public Enumeration<String> getKeys() {
        return messages != null ? Collections.enumeration(messages.keySet()) : parent.getKeys();
    }

    public DataSource getDataSource() {
        return dataSource;
    }


    /**
     *
     */
    public static class DBControl extends Control {

        private static Map bundleLabel;
        private static Map propertyLabel = new HashMap();
        private static Map transformerLabel = new HashMap();
        private static Map templateLabel = new HashMap();
        private static StringBuilder QUERY_NAME = new StringBuilder("SELECT * FROM MESSAGETRANSLATION ");

        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {

            if (!"GENERIC".equals(baseName))
                QUERY_NAME.append("WHERE COD_MC = ").append(baseName);

            bundleLabel = jdbcTemplate.query(QUERY_NAME.toString(),
                    new ResultSetExtractor<Map>() {
                        @Override
                        public Map extractData(ResultSet rs)
                                throws SQLException, DataAccessException {

                            return extractI18NData(rs);
                        }


                    });

            return new TextBundle(bundleLabel);
        }

        /**
         * Metodo que extrae la data del resultado del query ejecutado en la tabla <code>MESSAGETRANSLATION</code>
         * y lo coloca en un <code>Mapa</code>, donde el <code>key</code> del mapa esta compuesto por el identificador del
         * mensaje, el codigo de la categoria del mensaje y el locale al que pertenece el mensaje
         *
         * @param rs
         * @return
         */
        private Map extractI18NData(ResultSet rs) {
            Map mapLabels = new HashMap();
            try {
                while (rs.next()) {
                    mapLabels.put(buildBundleKey(rs.getString("IDMT"), rs.getString("CODMC"), rs.getString("LOCALE")), rs.getString("VALUE"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return mapLabels;
        }


        /**
         * @param key
         * @param codCategory
         * @param locale
         * @return
         */
        private static String buildBundleKey(String key, String codCategory, String locale) {
            return key.concat("_").concat(codCategory).concat("_").concat(locale);
        }

        /**
         * @param key
         * @param locale
         * @return
         */
        private static String buildBundleKeyCategory(String key, String locale) {
            return key.concat("_").concat(locale);
        }


        /**
         * @param keyName
         * @return
         */
        public static String getBundleLabel(String keyName) {

            String language = UserInfo.getLocaleUser().getLanguage();

            Map bundles = DBControl.getBundleLabel();
            String label = (String) bundles.get(buildBundleKeyCategory(keyName, language));
            if (label == null) {
                label = (String) bundles.get(buildBundleKeyCategory(keyName, language));
            }

            //Busqueda por segundo idioma en jerarquia
            if (label == null) {
                label = (String) bundles
                        .get(buildBundleKeyCategory(keyName, DolphinLocaleType.ENGLISH.getValue()));
            }

            if (label == null) {
                label = "[Label not found]";
            }

            return label;
        }


        /**
         * @param bundleName
         * @param keyName
         * @return
         */
        public static String getBundleLabel(String bundleName, String keyName) {

            String language_country = current_locale.getLanguage();

            Map bundles = DBControl.getBundleLabel();
            String label = (String) bundles.get(buildBundleKey(bundleName, keyName, language_country));
            if (label == null) {
                // language_country = UserInfo.getLanguage();
                label = (String) bundles.get(buildBundleKey(bundleName, keyName, language_country));
            }

            if (label == null) {
                label = (String) bundles
                        .get(buildBundleKey(bundleName, keyName, DolphinLocaleType.SPANISH.getValue()));
            }


            return label;
        }


        public static Map getBundleLabel() {
            return bundleLabel;
        }

        public static void setBundleLabel(Map bundleLabel) {
            DBControl.bundleLabel = bundleLabel;
        }

        public Map getPropertyLabel() {
            return propertyLabel;
        }

        public void setPropertyLabel(Map propertyLabel) {
            DBControl.propertyLabel = propertyLabel;
        }

        public Map getTransformerLabel() {
            return transformerLabel;
        }

        public void setTransformerLabel(Map transformerLabel) {
            DBControl.transformerLabel = transformerLabel;
        }

        public Map getTemplateLabel() {
            return templateLabel;
        }

        public void setTemplateLabel(Map templateLabel) {
            DBControl.templateLabel = templateLabel;
        }


    }

}
