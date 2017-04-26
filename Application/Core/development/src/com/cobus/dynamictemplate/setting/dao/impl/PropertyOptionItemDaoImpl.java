package com.cobus.dynamictemplate.setting.dao.impl;

import com.cobus.dynamictemplate.setting.dao.PropertyOptionItemDao;
import com.cobus.dynamictemplate.setting.model.PropertyOptionItem;
import com.cobus.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Title: PropertyOptionItemImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 27/07/2016.
 */
@Repository("propertyOptionItemDao")
public class PropertyOptionItemDaoImpl extends AbstractDao<Integer, Integer> implements PropertyOptionItemDao {

    private static final Logger log = LogManager.getLogger(PropertyOptionItemDaoImpl.class.getName());

    JdbcTemplate jdbcTemplate;

    @Override
    public void save(PropertyOptionItem optionItem) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PROPERTYOPTIONLIST ")
                .append("(IDPROPERTY, DESCRIPTION, VALUE) ")
                .append(" VALUES (?,?,?)");

        log.debug("sql: " + sql);

        jdbcTemplate.update(sql.toString(), optionItem.getPropertyId(),
                optionItem.getDescription(), optionItem.getValue());

    }

    @Override
    public void delete(PropertyOptionItem optionItem) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM PROPERTYOPTIONLIST ")
                .append("( WHERE IDPROPERTY = ? AND VALUE = ?) ")
                .append(" VALUES (?,?,?)");

        log.debug("sql: " + sql);

        jdbcTemplate.update(sql.toString(), optionItem.getPropertyId(), optionItem.getValue());

    }

    @Override
    public void update(PropertyOptionItem optionItemOld, float oldValue) {
        StringBuilder sql = new StringBuilder();
        jdbcTemplate = new JdbcTemplate(getDataSource());

        sql.append("UPDATE PROPERTYOPTIONLIST SET ")
                .append("DESCRIPTION = ? ,")
                .append("VALUE = ? ")
                .append("( WHERE IDPROPERTY = ? AND VALUE = ?) ");

        log.debug("sql: " + sql);

        jdbcTemplate.update(sql.toString(), optionItemOld.getDescription(), optionItemOld.getValue(),
                optionItemOld.getPropertyId(), oldValue);

    }

    @Override
    public PropertyOptionItem getOptionItemByDescription(PropertyOptionItem optionItem) {
        PropertyOptionItem property;
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder query = new StringBuilder();
        RowMapper<PropertyOptionItem> rm = BeanPropertyRowMapper.newInstance(PropertyOptionItem.class);

        query.append("SELECT * FROM PROPERTYOPTIONLIST WHERE IDPROPERTY = ? AND VALUE = ?");
        property = jdbcTemplate.queryForObject(query.toString(), new Object[]{optionItem.getPropertyId(), optionItem.getValue()}, rm);
        return property;
    }

    @Override
    public List<PropertyOptionItem> getPropertyOptionItemById(Integer propertyId) {
        List<PropertyOptionItem> propertyOptionItems = new ArrayList<>();
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String query = "SELECT * FROM PROPERTYOPTIONLIST WHERE IDPROPERTY = ?";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(query, propertyId);

        for (Map<String, Object> map : mapList){

            propertyOptionItems.add(new PropertyOptionItem((Integer) map.get("IDPOLIST"), (Integer) map.get("IDPROPERTYID"),
                    (String)map.get("DESCRIPTION"), (Float)map.get("VALUE")));
        }

        return propertyOptionItems;
    }
}
