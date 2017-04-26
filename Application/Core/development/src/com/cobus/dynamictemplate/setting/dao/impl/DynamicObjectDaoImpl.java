package com.cobus.dynamictemplate.setting.dao.impl;

import com.cobus.dynamictemplate.setting.bo.PropertyTree;
import com.cobus.dynamictemplate.setting.dao.DynamicObjectDao;
import com.cobus.dynamictemplate.setting.dao.DynamicObjectPropDao;
import com.cobus.dynamictemplate.setting.dao.IPropertyDao;
import com.cobus.dynamictemplate.setting.model.DynamicObject;
import com.cobus.dynamictemplate.setting.model.DynamicTableMaintenance;
import com.cobus.dynamictemplate.setting.model.Property;
import com.cobus.dynamictemplate.util.TemplateStatus;
import com.cobus.util.AbstractDao;
import com.cobus.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository("dynamicObjectDao")
public class DynamicObjectDaoImpl extends AbstractDao<Integer, DynamicObject> implements DynamicObjectDao {
    private static final Logger log = LogManager.getLogger(DynamicObjectDaoImpl.class.getName());
    //(DynamicObjectDaoImpl.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IPropertyDao iPropertyDao;

    private DynamicObjectPropDao dynamicObjectPropDao;

    @Override
    public void saveDO(DynamicObject dynamicObject) {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "INSERT INTO DYNAMICOBJECT "
                + "(NAME, CATEGORY) VALUES (?,?)";

        log.debug("sql INSERT: " + sql);

        DynamicTableMaintenance dtm = new DynamicTableMaintenance(dynamicObject.getName());

        try {
            jdbcTemplate.execute(dtm.getGenerateCreateQuery()); //Creando Tabla Dinamica
            jdbcTemplate.update(sql, dynamicObject.getName(), dynamicObject.getCategory());
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
        }

    }

    @Override
    public void deleteDO(DynamicObject dynamicObject) {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "DELETE FROM DYNAMICOBJECT WHERE NAME = ?";

        if (getCountRow(dynamicObject) > 0) {
            log.debug("Lanzar Mensaje de Validacion... Tabla contiene registros. "
                    + "Contacte con el Administrador");
        } else {
            DynamicTableMaintenance dtm = new DynamicTableMaintenance(dynamicObject.getName());
            try {
                log.debug("SQL DELETE: " + dynamicObject.getName());
                jdbcTemplate.execute(dtm.getGenerateDropQuery());
                jdbcTemplate.update(sql, dynamicObject.getName());
            } catch (Exception e) {
                log.debug("Error: " + e.getMessage());
            }
        }
    }

    @Override
    public void updateDO(DynamicObject dynamicObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DynamicObject getDynamicObjectById(Integer idDO) {
        DynamicObject dynamicObject;
        jdbcTemplate = new JdbcTemplate(getDataSource());

        String query = "SELECT * FROM DYNAMICOBJECT WHERE IDDYNAMICOBJECT   = ?";
        log.debug("Query = " + query);
        log.debug("idDO  = " + idDO);
        RowMapper<DynamicObject> rm = BeanPropertyRowMapper.newInstance(DynamicObject.class);
        dynamicObject = jdbcTemplate.queryForObject(query, new Object[]{idDO}, rm);
        return dynamicObject;

    }

    @Override
    public DynamicObject getDynamicObjectByName(String templateName) {
        DynamicObject dynamicObject;
        jdbcTemplate = new JdbcTemplate(getDataSource());

        String query = "SELECT * FROM DYNAMICOBJECT DO WHERE UPPER(DO.NAME) = UPPER(?)";
        log.debug("Query = " + query);
        log.debug("templateName  = " + templateName);
        RowMapper<DynamicObject> rm = BeanPropertyRowMapper.newInstance(DynamicObject.class);
        dynamicObject = jdbcTemplate.queryForObject(query, new Object[]{templateName}, rm);
        return dynamicObject;

    }

    @Override
    public List<DynamicObject> getDynamicObjectList() {
        List<DynamicObject> doList;
//        String sql = "SELECT * FROM DYNAMICOBJECT WHERE CATEGORY = ?";
        doList = findDynamicObjectList(null,null, TemplateStatus.ACTIVE.getValue());
        return doList;

    }

    @Override
    public HashMap<DynamicObject, List<PropertyTree>> getAllDynamicObject() {
        List<DynamicObject> dynamicObjectList;
        List<PropertyTree> propertyList;
        HashMap<DynamicObject, List<PropertyTree>> templateMap = new HashMap<>();

        /*String query = "SELECT * FROM DYNAMICOBJECT";
        dynamicObjectList = getJdbcTemplate().query(query,
                new BeanPropertyRowMapper(DynamicObject.class));*/


        dynamicObjectList = findDynamicObjectList(null, null, TemplateStatus.ACTIVE.getValue());

        for (DynamicObject dynamicObject : dynamicObjectList) {
            List<Integer> allPropertyIdByDynamicObject = dynamicObjectPropDao.getPropertyIdList(dynamicObject.getDoId());
            propertyList = new ArrayList<>();

            for (Integer idProperty : allPropertyIdByDynamicObject) {
                Property property = iPropertyDao.getPropertyById(idProperty);
                propertyList.add(new PropertyTree(property.getPropertyId(), property.getName()));

            }

            templateMap.put(dynamicObject, propertyList);
        }
        return templateMap;
    }


    @Override
    public DynamicObject getTemplate(Integer category, String templateName) {

        List<DynamicObject> dynamicObjectList;
        List<Property> propertyList;
        DynamicObject dyo = null;

        dynamicObjectList = findDynamicObjectList(templateName, category, TemplateStatus.ACTIVE.getValue());


        if (dynamicObjectList != null) {
            propertyList = new ArrayList<>();
            dyo = dynamicObjectList.get(0);
            List<Integer> allPropertyIdByDynamicObject = dynamicObjectPropDao.getPropertyIdList(dyo.getDoId());
            for (Integer idProperty : allPropertyIdByDynamicObject) {
                Property property = iPropertyDao.getPropertyById(idProperty);
                propertyList.add(property);
            }
            dyo.setPropertyList(propertyList);
        }
        return dyo;

    }


    public List<DynamicObject> findDynamicObjectList(String templateName, Integer category, Integer templateStatus) {
        List<DynamicObject> dynamicObjectList;
        Object[] objects = new Object[]{};
        StringBuilder query = new StringBuilder();
        StringBuilder sqlWhere = new StringBuilder();
        jdbcTemplate = new JdbcTemplate(getDataSource());

        query.append("SELECT * FROM DYNAMICOBJECT ");

        if(templateStatus != null || category != null || StringUtil.isEmptyOrNullValue(templateName)) {
            sqlWhere.append("DYO WHERE ");

            if (templateStatus != null) {
                sqlWhere.append(" UPPER(DYO.STATUS) = UPPER(?)");
                objects = new Object[]{templateStatus};
            }

            if (category != null) {

                objects = sqlWhere.indexOf("STATUS") > 0 ? new Object[]{templateStatus, category} :
                        new Object[]{category};

                if (sqlWhere.indexOf("?") > 0)
                    sqlWhere.append(" AND UPPER(DYO.CATEGORY) = UPPER(?)");
                else
                    sqlWhere.append(" UPPER(DYO.CATEGORY) = UPPER(?)");
            }

            if (!StringUtil.isEmptyOrNullValue(templateName)) {
                objects = sqlWhere.indexOf("STATUS") > 0 ? sqlWhere.indexOf("CATEGORY") > 0 ?
                        new Object[]{templateStatus, category, templateName} : new Object[]{templateStatus, templateName} :
                        new Object[]{templateName};

                if (sqlWhere.indexOf("?") > 0)
                    sqlWhere.append(" AND UPPER(DYO.NAME) = UPPER(?)");
                else
                    sqlWhere.append(" UPPER(DYO.NAME) = UPPER(?)");
            }
        }

        if (sqlWhere.length()>0){
            query.append(sqlWhere);
        }
        log.debug("sqlWhere: " + sqlWhere);
        log.debug("Query: " + query);

        dynamicObjectList = (sqlWhere.length()>0) ? jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper(DynamicObject.class), objects):
                jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper(DynamicObject.class)) ;

        return dynamicObjectList;
    }

    @Override
    public Integer getCountRow(DynamicObject dynamicObject) {
        Integer size;
        DynamicTableMaintenance dtm = new DynamicTableMaintenance(dynamicObject.getName());
        size = jdbcTemplate.queryForObject(dtm.getCountRowQuery(), Integer.class);
        return size;
    }


}
