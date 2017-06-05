package com.amsystem.bifaces.dynamictemplate.setting.dao.impl;

import com.amsystem.bifaces.dynamictemplate.setting.model.IFProperty;
import com.amsystem.bifaces.util.AbstractDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Title: PropertyDaoImpl.java
 *
 * @author jaguilar (JAR)
 * File Creation on 07/04/2016
 */
@Repository("propertyDaoImpl")
public class PropertyDaoImpl extends AbstractDao<Integer, Property> implements PropertyDao {

    private static final Logger log = LogManager.getLogger(PropertyDaoImpl.class.getName());

    private JdbcTemplate jdbcTemplate;

    private boolean flag;


    public boolean save(String propertyName) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        flag = true;

        try {
            String sql = "INSERT INTO PROPERTY ( NAME ) VALUES (?)";
            int rowAffected = (jdbcTemplate.update(sql, propertyName));
            flag = (rowAffected > 0);
            log.debug("Propiedad almacenada: " + propertyName + "\t return: " + rowAffected);
        } catch (Exception ex) {
            flag = false;
            log.error("No se pudo almacenar la propiedad: " + propertyName + "\t return: " + ex.getMessage());
        }
        return flag;

    }


    public boolean saveManualTransaction(String propertyName) {
        log.debug("*** Begin saveManualTransaction ****");
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        flag = true;

        try {
            dbConnection = getDataSource().getConnection();
            dbConnection.setAutoCommit(false);
            String sql = "INSERT INTO PROPERTY ( NAME ) VALUES (?)";
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, propertyName);

            int rowAffected = preparedStatement.executeUpdate();
            dbConnection.commit();
            flag = (rowAffected > 0);
            log.debug("Propiedad almacenada: " + propertyName + "\t return: " + rowAffected);
        }catch (Exception ex) {
            flag = false;
            log.error("No se pudo almacenar la propiedad: " + propertyName + "\t return: " + ex.getMessage());
            try{
                if(dbConnection!=null) {
                    dbConnection.rollback();
                }
            }catch(SQLException se2){
                se2.printStackTrace();
            }//end try
        }finally {
            try {
                if (preparedStatement != null) {  preparedStatement.close();  }
                if (dbConnection != null) { dbConnection.close(); }
            }catch (SQLException e){
                log.error("Error : " + e.getMessage());
            }
        }
        return flag;

    }


    public boolean update(IFProperty property) {
        log.debug("*** Begin update ****");
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String updateSQL = buildUpdateQueryProperty();
        try{
            int update = jdbcTemplate.update(updateSQL, property.getLabel(), property.getType(),
                    property.getRenderingType(), property.getExpressionValidator(), property.getFormula(),
                    property.isVisible() ? 1 : 0, property.isEditable() ? 1 : 0, property.isRequired() ? 1 : 0,
                    property.getParent(), property.getMask(), property.getDefaultValue(), property.getPropertyId());

            flag = (update > 0);
            log.debug("Propiedad actualizada: " + property.getName() + "\t return: " + update);
        }catch (Exception ex) {
            flag = false;
            log.error("No se pudo actualizar la propiedad: " + property.getName() + "\t return: " + ex.getMessage());
        }
        return flag;


    }


    public boolean updateManualTransaction(IFProperty property){
        log.debug("*** Begin updateManualTransaction ****");
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        flag = true;
        String updateSQL = buildUpdateQueryProperty();

        try{
            dbConnection = getDataSource().getConnection();
            dbConnection.setAutoCommit(false);
            preparedStatement = dbConnection.prepareStatement(updateSQL);

            preparedStatement.setString(1, property.getLabel());
            preparedStatement.setInt(2, property.getType());
            preparedStatement.setInt(3, property.getRenderingType());
            preparedStatement.setString(4, property.getExpressionValidator());
            preparedStatement.setString(5, property.getFormula());
            preparedStatement.setInt(6, property.isVisible() ? 1 : 0);
            preparedStatement.setInt(7, property.isEditable() ? 1 : 0);
            preparedStatement.setInt(8, property.isRequired() ? 1 : 0);
            preparedStatement.setString(9, property.getParent());
            preparedStatement.setString(10, property.getMask());
            preparedStatement.setString(11, property.getDefaultValue());
            preparedStatement.setInt(12, property.getPropertyId());

            int rowAffected = preparedStatement.executeUpdate();
            dbConnection.commit();

            flag = (rowAffected > 0);
            log.debug("Propiedad actualizada: " + property.getName() + "\t return: " + rowAffected);
        }catch (Exception ex) {
            flag = false;
            log.error("No se pudo actualizar la propiedad: " + property.getName() + "\t return: " + ex.getMessage());
            try{
                if(dbConnection!=null) {
                    dbConnection.rollback();
                }
            }catch(SQLException se1){
                log.error("Error : " + se1.getMessage());
            }//end try
        }finally {
            try {
                if (preparedStatement != null) {  preparedStatement.close();  }
                if (dbConnection != null) { dbConnection.close(); }
            }catch (SQLException se2){
                log.error("Error : " + se2.getMessage());
            }
        }

        return flag;
    }

    /**
     * Elimina una la propiedad en la base de datos
     *
     * @param propertyName nombre de la propiedad a ser eliminada
     * @return <tt>true</tt> Si la propiedad es eliminada con exito. <tt>false</tt> Si ocurre algun error.
     */
    public boolean delete(String propertyName) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        flag = true;
        try {
            String sql = "DELETE FROM PROPERTY WHERE NAME = ?";
            int update = (jdbcTemplate.update(sql, propertyName));
            flag = (update > 0);
            log.debug("Propiedad eliminada: " + propertyName + "\t return: " + update);
        } catch (Exception ex) {
            flag = false;
            log.error("No se pudo eliminar la propiedad: " + propertyName + "\t return: " + ex.getMessage());
        }
        return flag;

    }

    /**
     * Carga una propiedad de la base de datos a partir del identificador
     *
     * @param idProperty identificador de la propiedad
     * @return <tt>IFProperty</tt> si la propiedad existe. <tt>NULL</tt> En caso de que no exista
     */
    public IFProperty loadPropertyById(Integer idProperty) {
        IFProperty property;
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String queryLoad = buildDynamicSelectQueryProperty(true, false);
        RowMapper<Property> rm = BeanPropertyRowMapper.newInstance(Property.class);
        //query.append("SELECT * FROM PROPERTY WHERE IDPROPERTY = ?");
        property = jdbcTemplate.queryForObject(queryLoad, new Object[]{idProperty}, rm);

        return property;
    }

    /**
     * Carga una propiedad de la base de datos a que coincida con el nombre
     *
     * @param propertyName nombre de la propiedad
     * @return <tt>IFProperty</tt> si la propiedad existe. <tt>NULL</tt> En caso de que no exista
     */
    public IFProperty loadPropertyByName(String propertyName){
        IFProperty property;
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String queryLoad = buildDynamicSelectQueryProperty(false, true);
        RowMapper<Property> rm = BeanPropertyRowMapper.newInstance(Property.class);
        property = jdbcTemplate.queryForObject(queryLoad, new Object[]{propertyName}, rm);

        return property;
    }

    /**
     * Carga todas las propiedades almacenadas en la base de datos
     *
     * @return <tt>Lista</tt> de propiedades
     */
    public List<IFProperty> loadAllProperty() {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        List<IFProperty> propertyList = new ArrayList<>();
        String query = "SELECT * FROM PROPERTY";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(query);

        for (Map<String, Object> map : mapList) {
            IFProperty property = new Property((Integer) map.get("IDPROPERTY"), (String) map.get("NAME"), (String) map.get("LABEL"),
                    (Integer) map.get("PROPERTYTYPE"), (Integer) map.get("RENDERINGTYPE"),
                    (String) map.get("EXPRESIONVALIDATOR"), (String) map.get("FORMULA"), (String) map.get("DEFAULTVALUE"),
                    (Integer) map.get("VISIBLE") > 0 ? Boolean.TRUE : Boolean.FALSE, (Integer) map.get("EDITABLE") > 0 ? Boolean.TRUE : Boolean.FALSE,
                    (Integer) map.get("REQUIRED") > 0 ? Boolean.TRUE : Boolean.FALSE, (String) map.get("PARENTPROPERTY"), (String) map.get("MASK"));

            propertyList.add(property);
        }

        return propertyList;

    }


    /**
     * Carga todas las propiedades almacenadas en la base de datos dada una <tt>List</tt> de identificadores
     *
     * @return <tt>List</tt> de propiedades
     */
    public List<IFProperty> loadPropertyListByIdList(List idsProperty) {

        List<IFProperty> propertyList;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", idsProperty);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(getDataSource());
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append(buildDynamicSelectQueryProperty(false,false)).append(" IDPROPERTY IN (:ids)");
        propertyList = template.query(sqlSelect.toString(), parameters, new BeanPropertyRowMapper(Property.class));

        return propertyList;
    }

    /**
     * Construye la sentencia para la consulta de una propiedad segun los parametros recibidos
     *
     * @param propertyId identificador de la propiedad
     * @param propertyName nombre de la propiedad
     * @return <tt>String</tt> de sentecia
     */
    private String buildDynamicSelectQueryProperty(boolean propertyId, boolean propertyName){
        StringBuilder dynamicQuery = new StringBuilder();
        dynamicQuery.append("SELECT P.IDPROPERTY AS propertyId, P.NAME AS name, P.LABEL AS label, P.PROPERTYTYPE AS type, ")
                .append("P.RENDERINGTYPE AS renderingType, P.EXPRESIONVALIDATOR AS expressionValidator, P.FORMULA AS formula, ")
                .append("P.DEFAULTVALUE AS defaultValue, P.VISIBLE AS visible, P.EDITABLE AS editable, P.REQUIRED AS required, ")
                .append("P.PARENTPROPERTY AS parent, P.MASK AS mask ")
                .append("FROM PROPERTY P ")
                .append("WHERE ");

        if(propertyId)
            dynamicQuery.append(" P.IDPROPERTY = ? ");
        if(propertyName)
            dynamicQuery.append(" P.NAME = ? ");

        return  dynamicQuery.toString();
    }

    /**
     * Sentencia para actualizacion de campos en la tabla <tt>property</tt>
     *
     * @return <tt>String</tt> de sentecia
     */
    private String buildUpdateQueryProperty(){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE PROPERTY SET ")
                .append("LABEL = ? ,")
                .append("PROPERTYTYPE = ? ,")
                .append("RENDERINGTYPE = ? ,")
                .append("EXPRESIONVALIDATOR = ? ,")
                .append("FORMULA = ? ,")
                .append("VISIBLE = ? ,")
                .append("EDITABLE = ? ,")
                .append("REQUIRED = ? ,")
                .append("PARENTPROPERTY = ? , ")
                .append("MASK = ? , ")
                .append("DEFAULTVALUE = ? ")
                .append(" WHERE IDPROPERTY = ? ");

        log.debug("Update Sql: " + sql.toString());

        return sql.toString();
    }

}
