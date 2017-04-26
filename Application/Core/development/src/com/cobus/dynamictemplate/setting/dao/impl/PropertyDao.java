package com.cobus.dynamictemplate.setting.dao.impl;

import com.cobus.dynamictemplate.setting.dao.IPropertyDao;
import com.cobus.dynamictemplate.setting.dao.PropertyOptionItemDao;
import com.cobus.dynamictemplate.setting.model.Property;
import com.cobus.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Title: PropertyDaoImpl.java
 *
 * @author jaguilar (JAR) File Creation on 07/04/2016
 */
@Repository("propertyDao")
public class PropertyDao extends AbstractDao<Integer, Property> implements IPropertyDao {

    private static final Logger log = LogManager.getLogger(PropertyDao.class.getName());

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PropertyOptionItemDao propertyOptionItemDao;

    @Override
    public void save(String propertyName) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "INSERT INTO PROPERTY ( NAME ) VALUES (?)";
        log.debug("sql: " + sql);
        jdbcTemplate.update(sql, propertyName);
    }

    @Override
    public void delete(String propertyName) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "DELETE FROM PROPERTY WHERE NAME = ?";
        jdbcTemplate.update(sql, propertyName);

    }

    @Override
    public void update(Property property) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
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

        jdbcTemplate.update(sql.toString(), property.getLabel(), property.getType(),
                property.getRenderingType(), property.getExpressionValidator(), property.getFormula(),
                property.isVisible() ? 1 : 0, property.isEditable() ? 1 : 0, property.isRequired() ? 1 : 0,
                property.getParent(), property.getMask(), property.getDefaultValue(), property.getPropertyId());

    }

    @Override
    public Property getPropertyById(Integer idProperty) {
        Property property;
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder query = new StringBuilder();
        RowMapper<Property> rm = BeanPropertyRowMapper.newInstance(Property.class);
        query.append("SELECT * FROM PROPERTY WHERE IDPROPERTY = ?");
        property = jdbcTemplate.queryForObject(query.toString(), new Object[]{idProperty}, rm);


        return property;
    }

    /*@Override
    public List<Property> getAllProperty() {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        List<Property> propertyList = new ArrayList<>();
        String query = "SELECT * FROM PROPERTY";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(query);

        for (Map<String, Object> map : mapList) {

            Property property = new Property((Integer) map.get("PROPERTYID"), (String) map.get("NAME"), (String) map.get("LABEL"),
                    (Integer) map.get("PROPERTYTYPE"), (Integer) map.get("RENDERINGTYPE"),
                    (String) map.get("EXPRESIONVALIDATOR"), (String) map.get("FORMULA"), (String) map.get("DEFAULTVALUE"),
                    (Integer) map.get("VISIBLE") > 0 ? Boolean.TRUE : Boolean.FALSE, (Integer) map.get("EDITABLE") > 0 ? Boolean.TRUE : Boolean.FALSE,
                    (Integer) map.get("REQUIRED") > 0 ? Boolean.TRUE : Boolean.FALSE, (String) map.get("PARENTPROPERTY"), (String) map.get("MASK"));


            PropertyOptionItemDao optionItem = (PropertyOptionItemDao) BeanFactoryAwis.getBean("propertyOptionItemDao");
            List<PropertyOptionItem> optionItemList = optionItem.getPropertyOptionItemById(property.getPropertyId());
            property.setPropertyOptionItems(optionItemList);

            propertyList.add(property);
        }


        return propertyList;

    }*/


    @Override
    public List<Property> getAllProperty() {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        List<Property> propertyList = new ArrayList<>();
        String query = "SELECT * FROM PROPERTY";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(query);

        for (Map<String, Object> map : mapList) {

            Property property = new Property((Integer) map.get("IDPROPERTY"), (String) map.get("NAME"), (String) map.get("LABEL"),
                    (Integer) map.get("PROPERTYTYPE"), (Integer) map.get("RENDERINGTYPE"),
                    (String) map.get("EXPRESIONVALIDATOR"), (String) map.get("FORMULA"), (String) map.get("DEFAULTVALUE"),
                    (Integer) map.get("VISIBLE") > 0 ? Boolean.TRUE : Boolean.FALSE, (Integer) map.get("EDITABLE") > 0 ? Boolean.TRUE : Boolean.FALSE,
                    (Integer) map.get("REQUIRED") > 0 ? Boolean.TRUE : Boolean.FALSE, (String) map.get("PARENTPROPERTY"), (String) map.get("MASK"));


            propertyList.add(property);
        }


        return propertyList;

    }

    @Override
    public List<Property> getAllPropertyByIds(List idsProperty) {

        List<Property> propertyList;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", idsProperty);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(getDataSource());
        String sql = "SELECT * FROM PROPERTY WHERE IDPROPERTY IN (:ids)";
        propertyList = template.query(sql, parameters, new BeanPropertyRowMapper(Property.class));

        return propertyList;
    }

}
