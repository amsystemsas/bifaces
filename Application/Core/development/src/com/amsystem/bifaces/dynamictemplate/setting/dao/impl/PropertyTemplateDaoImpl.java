package com.amsystem.bifaces.dynamictemplate.setting.dao.impl;

import com.amsystem.bifaces.util.AbstractDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyTemplateDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Title: DynamicObjectPropDaoImpl.java
 *
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
@Repository("propertyTemplateDao")

public class PropertyTemplateDaoImpl extends AbstractDao<Integer, Integer> implements PropertyTemplateDao {

    private static final Logger log = LogManager.getLogger(PropertyTemplateDaoImpl.class.getName());

    private JdbcTemplate jdbcTemplate;


    @Override
    public boolean save(PropertyTemplate propertyTemplate) {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "INSERT INTO PROPERTYTEMPLATE (IDPROPERTY, IDTR, OPERATION_DATE) VALUES (?,?,?)";
        //Date date = new Date();
        int rowAffected = -1;
        log.debug("SAVE sql: " + sql);

        //TODO: Mudar a la capa de servicio
        /**
        List<Integer> propertyIdList = new ArrayList<>();
        propertyIdList.add(idProperty);
        List<Property> propertyList = propertyDao.getAllPropertyByIds(propertyIdList);
        Template template = idDynamicObject != null ? templateDao.getDynamicObjectById(idDynamicObject) : templateDao.getDynamicObjectByName(templateName);
        DynamicTableMaintenance dtm = new DynamicTableMaintenance(template.getName());
         */
        try {
            //String generateAlterAddQuery = dtm.getGenerateAlterAddQuery(propertyList);
            //log.debug("SQL Add Column: " + generateAlterAddQuery);
            //jdbcTemplate.execute(generateAlterAddQuery);
            rowAffected = jdbcTemplate.update(sql, propertyTemplate.getPropertyId(), propertyTemplate.getTemplateId(), propertyTemplate.getOperationDate());
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }

        return rowAffected>0;
    }

    @Override
    public boolean saveBatch(final List<PropertyTemplate> propertyOptionItems) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PROPERTYTEMPLATE ")
                .append("(IDPROPERTY, IDTR, OPERATION_DATE) ")
                .append(" VALUES (?,?,?)");

        log.debug("sql: " + sql);

        int rowAffected = jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {

                PropertyTemplate propertyTemplate = propertyOptionItems.get(i);
                ps.setInt(1, propertyTemplate.getPropertyId());
                ps.setInt(2, propertyTemplate.getTemplateId());
                ps.setDate(3, (new Date(propertyTemplate.getOperationDate().getTime())));

            }

            @Override
            public int getBatchSize() {
                return propertyOptionItems.size();
            }
        }).length;

        log.debug("Filas almacenadas: " + rowAffected);
        return rowAffected > 0;

    }

    @Override
    public boolean delete(PropertyTemplate propertyTemplate) {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "DELETE FROM PROPERTYTEMPLATE WHERE IDPROPERTY = ? AND IDTR = ?";
        int rowAffected = -1;
        //log.debug("SQL DELETE: " + idProperty);

        /*
        List<Integer> propertyIdList = new ArrayList<>();
        propertyIdList.add(idProperty);
        List<Property> propertyList = propertyDao.getAllPropertyByIds(propertyIdList);
        Template template = idDynamicObject != null ? templateDao.getDynamicObjectById(idDynamicObject) : templateDao.getDynamicObjectByName(templateName);
        DynamicTableMaintenance dtm = new DynamicTableMaintenance(template.getName());
        */
        try {
            //String generateAlterDropQuery = dtm.getGenerateAlterDropQuery(propertyList);
            //log.debug("SQL Drop Column: " + generateAlterDropQuery);
            //jdbcTemplate.execute(generateAlterDropQuery);
            rowAffected = jdbcTemplate.update(sql, propertyTemplate.getPropertyId(), propertyTemplate.getTemplateId());
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
        }

        return rowAffected>0;
    }

    @Override
    public boolean isPropertyAssociatedToTemplate(Integer idProperty) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "SELECT COUNT(IDTR) FROM PROPERTYTEMPLATE WHERE IDPROPERTY = ?";
        Integer rowCount = jdbcTemplate.queryForObject(sql, new Object[]{idProperty}, Integer.class);
        return rowCount >0;

    }

    @Override
    public boolean hasTemplateProperties(Integer templateId) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "SELECT COUNT(IDPROPERTY) FROM PROPERTYTEMPLATE WHERE IDTR = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{templateId}, Integer.class)>0;

    }

    @Override
    public PropertyTemplate getDynamicObjectPropertyById(Integer idProperty, Integer idDynamicObject) {
        PropertyTemplate dop;
        jdbcTemplate = new JdbcTemplate(getDataSource());

        String sql = "SELECT * FROM PROPERTYTEMPLATE WHERE IDPROPERTY = ? AND IDTR = ?";
        dop = jdbcTemplate.queryForObject(sql, new Object[]{idProperty, idDynamicObject}, PropertyTemplate.class);
        return dop;
    }

    @Override
    public List<Integer> loadPropertyByTemplateId(Integer templateId) {
        List<Integer> dopList;
        jdbcTemplate = new JdbcTemplate(getDataSource());

        String sql = "SELECT IDPROPERTY FROM PROPERTYTEMPLATE WHERE IDTR = ?";
        dopList = jdbcTemplate.queryForList(sql, new Object[]{templateId}, Integer.class);

        return dopList;
    }

    public List<Integer> loadTemplateByPropertyId(Integer propertyId) {
        List<Integer> dopList;
        jdbcTemplate = new JdbcTemplate(getDataSource());

        String sql = "SELECT IDTR FROM PROPERTYTEMPLATE WHERE IDPROPERTY = ?";
        dopList = jdbcTemplate.queryForList(sql, new Object[]{propertyId}, Integer.class);

        return dopList;
    }

}
