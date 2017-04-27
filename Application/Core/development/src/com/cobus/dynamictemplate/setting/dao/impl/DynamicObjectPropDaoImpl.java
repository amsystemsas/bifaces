package com.cobus.dynamictemplate.setting.dao.impl;

import com.cobus.dynamictemplate.setting.dao.DynamicObjectDao;
import com.cobus.dynamictemplate.setting.dao.DynamicObjectPropDao;
import com.cobus.dynamictemplate.setting.dao.IPropertyDao;
import com.cobus.dynamictemplate.setting.model.DynamicObject;
import com.cobus.dynamictemplate.setting.model.DynamicObjectProperty;
import com.cobus.dynamictemplate.setting.model.DynamicTableMaintenance;
import com.cobus.dynamictemplate.setting.model.Property;
import com.cobus.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Title: DynamicObjectPropDaoImpl.java
 *
 * @author jaguilar (JAR) File Creation on 24/04/2016
 */
@Repository("dynamicObjectPropDao")
public class DynamicObjectPropDaoImpl extends AbstractDao<Integer, Integer> implements DynamicObjectPropDao {

    private JdbcTemplate jdbcTemplate;
    private static final Logger log = LogManager.getLogger(DynamicObjectPropDaoImpl.class.getName());

    @Autowired
    private IPropertyDao iPropertyDao;

    @Autowired
    private DynamicObjectDao dynamicObjectDao;

    @Override
    public void save(Integer idProperty, Integer idDynamicObject, String templateName) {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "INSERT INTO DYNAMICOBJECTPROPERTY (IDPROPERTY, IDDYNAMICOBJECT, OPERATIONDATE) VALUES (?,?,?)";
        Date date = new Date();
        log.debug("SAVE sql: " + sql);

        //PropertyService propertyBo = (PropertyService) BeanFactoryAwis.getBean("propertyService");

        //DynamicObjectService dynamicObjectBo = (DynamicObjectService) BeanFactoryAwis.getBean("dynamicObjectBo");

        List<Integer> propertyIdList = new ArrayList<>();
        propertyIdList.add(idProperty);
        List<Property> propertyList = iPropertyDao.getAllPropertyByIds(propertyIdList);
        DynamicObject dynamicObject = idDynamicObject != null ? dynamicObjectDao.getDynamicObjectById(idDynamicObject) : dynamicObjectDao.getDynamicObjectByName(templateName);
        DynamicTableMaintenance dtm = new DynamicTableMaintenance(dynamicObject.getName());
        try {
            String generateAlterAddQuery = dtm.getGenerateAlterAddQuery(propertyList);
            log.debug("SQL Add Column: " + generateAlterAddQuery);
            jdbcTemplate.execute(generateAlterAddQuery);
            jdbcTemplate.update(sql, idProperty, dynamicObject.getDoId(), date);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer idProperty, Integer idDynamicObject, String templateName) {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "DELETE FROM DYNAMICOBJECTPROPERTY WHERE IDPROPERTY = ? AND IDDYNAMICOBJECT = ?";
        log.debug("SQL DELETE: " + idProperty);

        List<Integer> propertyIdList = new ArrayList<>();
        propertyIdList.add(idProperty);
        List<Property> propertyList = iPropertyDao.getAllPropertyByIds(propertyIdList);
        DynamicObject dynamicObject = idDynamicObject != null ? dynamicObjectDao.getDynamicObjectById(idDynamicObject) : dynamicObjectDao.getDynamicObjectByName(templateName);
        DynamicTableMaintenance dtm = new DynamicTableMaintenance(dynamicObject.getName());
        try {
            String generateAlterDropQuery = dtm.getGenerateAlterDropQuery(propertyList);
            log.debug("SQL Drop Column: " + generateAlterDropQuery);
            jdbcTemplate.execute(generateAlterDropQuery);
            jdbcTemplate.update(sql, idProperty, idDynamicObject);
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
        }

    }

    @Override
    public DynamicObjectProperty getDynamicObjectPropertyById(Integer idProperty, Integer idDynamicObject) {
        DynamicObjectProperty dop;
        jdbcTemplate = new JdbcTemplate(getDataSource());

        String sql = "SELECT * FROM DYNAMICOBJECTPROPERTY WHERE IDPROPERTY = ? AND IDDYNAMICOBJECT = ?";
        dop = jdbcTemplate.queryForObject(sql, new Object[]{idProperty, idDynamicObject}, DynamicObjectProperty.class);
        return dop;
    }

    @Override
    public List<Integer> getPropertyIdList(Integer idDynamicObject) {
        List<Integer> dopList;
        jdbcTemplate = new JdbcTemplate(getDataSource());

        String sql = "SELECT IDPROPERTY FROM DYNAMICOBJECTPROPERTY WHERE IDDYNAMICOBJECT = ?";
        dopList = jdbcTemplate.queryForList(sql, new Object[]{idDynamicObject}, Integer.class);

        return dopList;
    }

}
