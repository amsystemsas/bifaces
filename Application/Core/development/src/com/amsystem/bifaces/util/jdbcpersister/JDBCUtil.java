package com.amsystem.bifaces.util.jdbcpersister;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Title: JDBCUtil.java
 * @author jaguilar (JAR)
 * File Creation on 09/04/2016
 */
public class JDBCUtil {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    
    public JDBCUtil() {     
    }
    
    public JDBCUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }
       
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplateObject() {
        return jdbcTemplateObject;
    }

    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }
    
    public Connection openUserDbConnection() throws SQLException{
        return getDataSource().getConnection();
    }

}
