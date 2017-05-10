package com.amsystem.bifaces.dynamictemplate.setting.dao.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyOptionItemDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItem;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class PropertyOptionItemDaoImpl extends AbstractDao<Integer, PropertyOptionItem> implements PropertyOptionItemDao {

    private static final Logger log = LogManager.getLogger(PropertyOptionItemDaoImpl.class.getName());

    private JdbcTemplate jdbcTemplate;

    /**
     * Guarda un nuevo Item en la base de datos
     *
     * @param optionItem Item a almacenar
     * @return <tt>true</tt> Si el Item es almacenado con exito. <tt>false</tt> Si ocurre algun error.
     */
    public boolean save(PropertyOptionItem optionItem) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PROPERTYOPTIONITEM ")
                .append("(IDPROPERTY, DESCRIPTION, VALUE) ")
                .append(" VALUES (?,?,?)");

        log.debug("sql: " + sql);

        int rowAffected = jdbcTemplate.update(sql.toString(), optionItem.getPropertyId(),
                optionItem.getDescription(), optionItem.getValue());

        return rowAffected > 0;

    }

    /**
     * Elimina de la base de datos el Item que recibe como parametro
     *
     * @param optionItem Item a ser eliminado
     * @return <tt>true</tt> Si el Item es eliminado con exito. <tt>false</tt> Si ocurre algun error.
     */
    public boolean delete(PropertyOptionItem optionItem) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM PROPERTYOPTIONITEM ")
                .append("( WHERE IDPROPERTY = ? AND VALUE = ?) ")
                .append(" VALUES (?,?,?)");

        log.debug("sql: " + sql);

        int rowAffected = jdbcTemplate.update(sql.toString(), optionItem.getPropertyId(), optionItem.getValue());

        return rowAffected>0;

    }

    /**
     * Actualiza los valores en base de datos del Item que recibe por parametro
     *
     * @param propertyOptionItem Item a ser actualizao
     * @return <tt>true</tt> Si el Item es actualizado con exito. <tt>false</tt> Si ocurre algun error.
     */
    @Override
    public boolean updateOptionItem(PropertyOptionItem propertyOptionItem) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE PROPERTYOPTIONITEM SET ")
                .append("DESCRIPTION = ? ,")
                .append("VALUE = ? ")
                .append("( WHERE IDPROPERTY = ? ) ");

        log.debug("sql: " + sql);

        int rowAffected = jdbcTemplate.update(sql.toString(), propertyOptionItem.getDescription(), propertyOptionItem.getValue(),
                propertyOptionItem.getPropertyItemId());

        return rowAffected>0;

    }


    /**
     * Carga los Item de una propiedad cuyo identificador coincidad con el que recibe por parametro
     *
     * @param propertyId Identificador de la propiedad
     * @return <tt>List</tt> de Item si existen registros con el identificador. <tt>List Empty</tt> si no existen registros.
     */
    public List<PropertyOptionItem> loadPropertyOptionItem(Integer propertyId) {
        List<PropertyOptionItem> propertyOptionItems = new ArrayList<>();
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String query = "SELECT * FROM PROPERTYOPTIONITEM WHERE IDPROPERTY = ?";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(query, propertyId);

        for (Map<String, Object> map : mapList){
            propertyOptionItems.add(new PropertyOptionItem((Integer) map.get("IDPOI"), (Integer) map.get("IDPROPERTY"),
                    (Float)map.get("VALUE"), (String)map.get("DESCRIPTION"), null));
        }

        return propertyOptionItems;
    }
}
