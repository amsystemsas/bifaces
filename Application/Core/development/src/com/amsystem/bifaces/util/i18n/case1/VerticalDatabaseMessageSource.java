package com.amsystem.bifaces.util.i18n.case1;

import org.springframework.dao.DataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Title: VerticalDatabaseMessageSource.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 16/10/2016.
 */
public class VerticalDatabaseMessageSource extends DatabaseMessageSourceBase {

    private static final String I18N_QUERY = "SELECT * FROM MESSAGETRANSLATION";

    @Override
    protected String getI18NSqlQuery() {
        return I18N_QUERY;
    }

    @Override
    protected Messages extractI18NData(ResultSet rs) throws SQLException, DataAccessException {

        Messages messages = new Messages();
        while (rs.next()) {
            Locale locale = new Locale(rs.getString("locale"));
            messages.addMessage(rs.getString("category").concat("_").concat(rs.getString("id_mt")), locale, rs.getString("message"));
        }
        return messages;
    }
}
