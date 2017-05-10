package com.amsystem.bifaces.util.i18n.case2;

import com.amsystem.bifaces.UserInfo;
import com.amsystem.bifaces.util.DolphinLocaleType;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Title: DolphinLabels.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 17/10/2016.
 */

public class DolphinLabels {

    private Map propertyLabel = new HashMap();
    private Map transformerLabel = new HashMap();
    private Map templateLabel = new HashMap();
    private static Map bundle = new HashMap();
    private static Map bundleLabel;
    private static DolphinLabels DOLPHIN_LABELS;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SessionFactory sessionFactory;


    private static JdbcTemplate jdbcTemplate;

    private static Locale current_locale;

    private DolphinLabels() {
        System.out.println("[***** INIT DolphinLabels ******]");
        System.out.println("[*** DolphinLabels ***] dataSource: " + dataSource);
        System.out.println("[*** DolphinLabels ***] sessionFactory: " + sessionFactory);

        /*
        if(jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(getDataSource());
        }
        */
    }


    public static synchronized DolphinLabels createSingletonInstance() {
        if (DOLPHIN_LABELS == null) {
            DOLPHIN_LABELS = new DolphinLabels();
        }

        return DOLPHIN_LABELS;

    }

    @Override
    public DolphinLabels clone() {
        try {
            throw new CloneNotSupportedException();
        } catch (CloneNotSupportedException ex) {
            System.out.println("No se puede clonar un objeto de la clase SoyUnico");
        }
        return null;
    }


    @PostConstruct
    public void init() {
        System.out.println("[Text] Initializing message source using query [{}]: " + getDataSource());
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(getDataSource());
        }

        System.out.println("[Text] Initializing message : " + jdbcTemplate);
        current_locale = UserInfo.getLocaleUser();
        // setParent(ResourceBundle.getBundle(BASE_NAME, current_locale , DB_CONTROL));
    }

    protected DataSource getDataSource() {
        return dataSource;
    }


    public static Map getBundleLabel() {
        return bundleLabel;
    }

    public static void setBundleLabel(Map bundleLabel) {
        DolphinLabels.bundleLabel = bundleLabel;
    }

    public Map getPropertyLabel() {
        return Collections.unmodifiableMap(DOLPHIN_LABELS.propertyLabel);
    }

    public void setPropertyLabel(Hashtable propertyLabel) {
        this.propertyLabel = propertyLabel;
    }

    public Map getTransformerLabel() {
        return Collections.unmodifiableMap(DOLPHIN_LABELS.transformerLabel);
    }

    public void setTransformerLabel(Hashtable transformerLabel) {
        this.transformerLabel = transformerLabel;
    }

    public Map getTemplateLabel() {
        return Collections.unmodifiableMap(DOLPHIN_LABELS.templateLabel);
    }

    public void setTemplateLabel(Hashtable templateLabel) {
        this.templateLabel = templateLabel;
    }

    public static Map getBundle() {
        return Collections.unmodifiableMap(DOLPHIN_LABELS.bundle);
    }

    public static void setBundle(Hashtable bundle) {
        DolphinLabels.bundle = bundle;
    }

    public static void loadCentralTransformerLabels() {
        /*if(Locale.getDefault() == null)
            loadTransformerLabels(ACSELE_LABELS.transformerLabels, "transformerlabels.load");
        else
            loadTransformerLabels(ACSELE_LABELS.transformerLabels, "transformerlabels.loadByLanguages", Locale.getDefault().toString());*/
    }

    public static void loadCentralPropertiesLabels() {
        /*if(UserInfo.getLocale() == null)
            loadLabels(ACSELE_LABELS.propertyLabels, "labels.load");
        else
            loadLabels(ACSELE_LABELS.propertyLabels, "labels.loadByLanguages", Locale.getDefault().toString());*/
    }

    public static void loadCentralTemplateLabels() {
        /*if(UserInfo.getLocale() == null)
            loadLabels(ACSELE_LABELS.templateLabels, "TemplateLabel.load");
        else
            loadLabels(ACSELE_LABELS.templateLabels, "TemplateLabel.loadByLanguages",UserInfo.getLocale().toString());*/
    }

    public static void loadCentralTransformerLabels(String locate) {
       /* loadTransformerLabels(DOLPHIN_LABELS.transformerLabel, "transformerlabels.loadByLanguages", locate);*/
    }

    public static void loadCentralPropertiesLabels(String locate) {
        loadLabels(DOLPHIN_LABELS.propertyLabel, "labels.loadByLanguages", locate);
    }

    public static void loadCentralTemplateLabels(String locate) {
        loadLabels(DOLPHIN_LABELS.templateLabel, "TemplateLabel.loadByLanguages", locate);
    }


    public static void loadCentralBundles() {
        loadBundles(DOLPHIN_LABELS.bundle, "SELECT * FROM MESSAGETRANSLATION");
    }


    /** ******************************************************************************************** */


    private static void loadBundles(Map bundles, String queryName) {
        bundles.clear();
        loadAllBundles(bundles, queryName);

    }


    private static void loadAllBundles(Map bundles, String queryName) {
        //Criteria crit = sessionStatic.createCriteria(MessageTranslation.class);
        //Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);
        //jdbcTemplate = new JdbcTemplate(dataSourceStatic);
        // List<GenericMessageTranslation> list = crit.list();

        //List<MessageTranslation> allMessage = messageTranslationDao.findAllMessage();

        bundles = jdbcTemplate.query(queryName, new ResultSetExtractor<Map>() {
            @Override
            public Map extractData(ResultSet rs)
                    throws SQLException, DataAccessException {

                return extractI18NData(rs);
            }
        });



    }

    private static Map extractI18NData(ResultSet rs) throws SQLException, DataAccessException {
        Map mapLabels = new HashMap();
        while (rs.next()) {
            mapLabels.put(makeBundleKey(rs.getString("category"), rs.getString("id_mt"), rs.getString("locale")), rs.getString("message"));

        }
        return mapLabels;
    }

    private static String makeBundleKey(String category, String key, String locale) {
        return key.concat("_").concat(category).concat("_").concat(locale);
    }


    private static void loadCategories(Hashtable bundleCategory, String queryName, String categoryName) {
        //SessionFactory sessionFactory = (SessionFactory)BeanFactoryAwis.getBean("propertyBo");
        SessionFactory sessionFactory = null;
        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);
        List<GenericMessageTranslation> list = query.list();
        for(GenericMessageTranslation genericCategoryLabel:list){
            bundleCategory.put(makeCategoryKey(genericCategoryLabel.getKey(), genericCategoryLabel.getLocale()), genericCategoryLabel.getMessage());
        }

    }

    private static String makeCategoryKey(String key, String locale) {
        return key.concat("_").concat(locale);

    }

    public static String getBundleLabel(String bundleName, String keyName) {

        String language_country = Locale.ENGLISH.getLanguage();
                //UserInfo.getLocaleString();

        Map bundles = DolphinLabels.bundle;
        String label = (String) bundles.get(makeBundleKey(bundleName, keyName, language_country));
        if (label == null) {
           // language_country = UserInfo.getLanguage();
            label = (String) bundles.get(makeBundleKey(bundleName, keyName, language_country));
        }
/*
        if (label == null && UserInfo.getLocale()!=null) {
            language_country = UserInfo.getLocale().getLanguage();
            label = (String) bundles.get(makeBundleKey(bundleName, keyName, language_country));
        }
*/
        if (label == null) {
            label = (String) bundles
                    .get(makeBundleKey(bundleName, keyName, DolphinLocaleType.DEFAULT_VALUE.getValue()));
        }

//         log.debug("457- label = '" + label + "'");
        if (label == null) {
            label = buildCatchAllValue(keyName);
        }
        return label;
    }

    private static String buildCatchAllValue(String keyName) {
        return null;
    }


    private static void loadLabels(Map templateLabel, String s, String locate) {
    }

    public static Object extractKey(String key) {
        return null;
    }
}
