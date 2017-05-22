package com.amsystem.bifaces.dynamictemplate.setting.dao.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.TemplateDao;
import com.amsystem.bifaces.util.AbstractDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.DynamicTableMaintenance;
import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.dynamictemplate.util.TemplateStatus;
import com.amsystem.bifaces.util.CategoryName;
import com.amsystem.bifaces.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("templateDao")
public class TemplateDaoImpl extends AbstractDao<Integer, Template> implements TemplateDao {

    private static final Logger log = LogManager.getLogger(TemplateDaoImpl.class.getName());

    private JdbcTemplate jdbcTemplate;



    @Override
    public boolean saveTemplate(Template template) {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        int rowAffected = -1;
        String sql = "INSERT INTO TEMPLATEREPOSITORY (IDCT, NAME) VALUES (?,?)";

        log.debug("sql INSERT: " + sql);

        DynamicTableMaintenance dtm = new DynamicTableMaintenance(template.getName(), template.getCategoryId());

        try {
            rowAffected = jdbcTemplate.update(sql, template.getCategoryId(), template.getName());
            if(rowAffected > 0)jdbcTemplate.execute(dtm.getGenerateCreateQuery()); //Creando Tabla Dinamica
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
        }

        return (rowAffected > 0);

    }

    @Override
    public boolean deleteTemplate(Template template) {

        jdbcTemplate = new JdbcTemplate(getDataSource());
        int rowAffected = -1;
        String sql = "DELETE FROM TEMPLATEREPOSITORY WHERE NAME = ?";

        if (getCountRowTemplate(template) > 0) {
            log.debug("Lanzar Mensaje de Validacion... Tabla contiene registros. "
                    + "Contacte con el Administrador");
        } else {
            DynamicTableMaintenance dtm = new DynamicTableMaintenance(template.getName(), template.getCategoryId());
            try {
                log.debug("SQL DELETE: " + template.getName());
                jdbcTemplate.execute(dtm.getGenerateDropQuery());
                rowAffected = jdbcTemplate.update(sql, template.getName());
            } catch (Exception e) {
                log.debug("Error: " + e.getMessage());
            }
        }

        return rowAffected>0;
    }

    @Override
    public boolean updateTemplate(Template template) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Template loadTemplateById(Integer idTr) {
        Template template;
        jdbcTemplate = new JdbcTemplate(getDataSource());

        String query = "SELECT * FROM TEMPLATEREPOSITORY WHERE IDTR = ?";
        log.debug("Query = " + query);
        log.debug("idTR  = " + idTr);
        RowMapper<Template> rm = BeanPropertyRowMapper.newInstance(Template.class);
        template = jdbcTemplate.queryForObject(query, new Object[]{idTr}, rm);

        return template;

    }

    @Override
    public Template loadTemplateByName(String templateName) {
        Template template;
        jdbcTemplate = new JdbcTemplate(getDataSource());

        String query = "SELECT TR.IDTR AS templateId, TR.IDCT AS categoryId, TR.NAME AS name, TR.STATUS AS status FROM TEMPLATEREPOSITORY TR WHERE UPPER(TR.NAME) = UPPER(?)";
        log.debug("Query = " + query);
        log.debug("templateName  = " + templateName);
        RowMapper<Template> rm = BeanPropertyRowMapper.newInstance(Template.class);
        template = jdbcTemplate.queryForObject(query, new Object[]{templateName}, rm);
        return template;

    }

    @Override
    public List<Template> loadTemplateByStatus(TemplateStatus templateStatus) {
        List<Template> doList;
        doList = findDynamicObjectList(null,null,templateStatus.getValue());
        return doList;

    }

    @Override
    public List<Template> loadTemplateByCategory(CategoryName categoryTemplateName) {
        List<Template> doList;
        doList = findDynamicObjectList(null, categoryTemplateName.getValue(), null);
        return doList;

    }

    @Override
    public List<Template> loadAllTemplate() {
        List<Template> doList;
        doList = findDynamicObjectList(null,null,null);
        return doList;

    }

    @Override
    public List<Template> loadTemplateListByIdList(List templateId) {
        List<Template> templateList;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", templateId);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(getDataSource());
        String sql = "SELECT * FROM TEMPLATEREPOSITORY WHERE IDTR IN (:ids)";
        templateList = template.query(sql, parameters, new BeanPropertyRowMapper(Template.class));

        return templateList;
    }

    /**
     * Consulta las plantillas existentes en la base de datos de acuerdo al Nombre, Categoria y/o Estatus
     *
     * @param templateName
     * @param category
     * @param templateStatus
     * @return
     */
    private List<Template> findDynamicObjectList(String templateName, Integer category, Integer templateStatus) {
        List<Template> templateList;
        Object[] objects = new Object[]{};
        StringBuilder query = new StringBuilder();
        StringBuilder sqlWhere = new StringBuilder();
        jdbcTemplate = new JdbcTemplate(getDataSource());

        query.append("SELECT TR.IDTR AS templateId, TR.IDCT AS categoryId, TR.NAME AS name, TR.STATUS AS status ")
            .append(" FROM TEMPLATEREPOSITORY TR");

        if(templateStatus != null || category != null || !StringUtil.isEmptyOrNullValue(templateName)) {
            sqlWhere.append("WHERE ");

            //Estatus de la plantilla
            if (templateStatus != null) {
                sqlWhere.append(" UPPER(TR.STATUS) = UPPER(?)");
                objects = new Object[]{templateStatus};
            }

            //Categoria de la plantilla
            if (category != null) {
                objects = sqlWhere.indexOf("STATUS") > 0 ? new Object[]{templateStatus, category} :
                        new Object[]{category};

                if (sqlWhere.indexOf("?") > 0)
                    sqlWhere.append(" AND UPPER(TR.IDCT) = UPPER(?)");
                else
                    sqlWhere.append(" UPPER(TR.IDCT) = UPPER(?)");
            }

            //Nombre Plantilla
            if (!StringUtil.isEmptyOrNullValue(templateName)) {
                objects = sqlWhere.indexOf("STATUS") > 0 ? sqlWhere.indexOf("IDCT") > 0 ?
                        new Object[]{templateStatus, category, templateName} : new Object[]{templateStatus, templateName} :
                        new Object[]{templateName};

                if (sqlWhere.indexOf("?") > 0)
                    sqlWhere.append(" AND UPPER(TR.NAME) = UPPER(?)");
                else
                    sqlWhere.append(" UPPER(TR.NAME) = UPPER(?)");
            }
        }

        if (sqlWhere.length()>0){
            query.append(sqlWhere);
        }
        log.debug("sqlWhere: " + sqlWhere);
        log.debug("Query: " + query);

        templateList = (sqlWhere.length()>0) ? jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper(Template.class), objects):
                jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper(Template.class)) ;

        return templateList;
    }

    @Override
    public Integer getCountRowTemplate(Template template) {
        Integer size;
        DynamicTableMaintenance dtm = new DynamicTableMaintenance(template.getName(), template.getCategoryId());
        size = jdbcTemplate.queryForObject(dtm.getCountRowQuery(), Integer.class);
        return size;
    }


}
