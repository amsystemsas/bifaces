package com.cobus.dynamictemplate.setting.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Title: DynamicTableMaintenance.java
 * @author jaguilar (JAR)
 * File Creation on 05/07/2016
 */
public class DynamicTableMaintenance extends DynamicObject implements IFDynamicTableMaintenance{
    private static final String SUFFIX_STRING = "STR";
    private static final String SUFFIX_VALUE = "VALUE";
    private static final Logger log = LogManager.getLogger(DynamicTableMaintenance.class.getName());

    public DynamicTableMaintenance(String desc) {
        super(desc);
    }   
    

    @Override
    public String getGenerateCreateQuery() {
        log.debug("** Creando Tabla Dinamica**");
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ").append(getName()).append(" (IDDT INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador Tabla Dinamica Generada', ");
        query.append("PRIMARY KEY (IDDT   ) COMMENT '')");
        return query.toString();
        
    }

    @Override
    public String getGenerateDropQuery() {
        StringBuilder query = new StringBuilder();
        query.append("DROP TABLE IF EXISTS ").append(getName());
        return query.toString();
    } 

    @Override
    public String getGenerateAlterAddQuery(List<Property> propertyList) {
        StringBuilder query = new StringBuilder();
        query.append("ALTER TABLE ").append(getName().toUpperCase());
        for(Property prop : propertyList){
            query.append(" ADD COLUMN ").append(prop.getName().toUpperCase()).append(SUFFIX_STRING);
            query.append(" VARCHAR(1000) NULL, ");
            query.append(" ADD COLUMN ").append(prop.getName().toUpperCase()).append(SUFFIX_VALUE);
            query.append(" VARCHAR(1000) NULL ");
            
        }
               
        return query.toString();
        
    }
    
    @Override
    public String getGenerateAlterDropQuery(List<Property> propertyList) {
        StringBuilder query = new StringBuilder();
        query.append("ALTER TABLE ").append(getName().toUpperCase());
        for(Property prop : propertyList){
            query.append(" DROP COLUMN ").append(prop.getName().toUpperCase()).append(SUFFIX_STRING).append(", ");
            query.append(" DROP COLUMN ").append(prop.getName().toUpperCase()).append(SUFFIX_VALUE);
            
            
        }
               
        return query.toString();
        
    }

    @Override
    public String getCountRowQuery() {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT COUNT(*) FROM ").append(getName());
        return query.toString();
    }
    
    
    
    
   

}
