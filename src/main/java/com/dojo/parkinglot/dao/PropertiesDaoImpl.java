package com.dojo.parkinglot.dao;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.tools.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

@Component
public class PropertiesDaoImpl implements PropertiesDao{
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private JdbcTemplate template;
    private SimpleJdbcInsert insert_property;

    private static final String PROPERTIES_TABLE = "PARKINGLOTPROPERTIES";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
        insert_property = new SimpleJdbcInsert(dataSource)
                .withTableName(PROPERTIES_TABLE)
                .usingColumns("name", "genericSize", "electricSize")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void setup () {
        createTable("create table " + PROPERTIES_TABLE + " (id INT GENERATED ALWAYS AS IDENTITY, name VARCHAR(40), genericSize INT, electricSize INT)");
    }

    @Override
    public ParkingLotProperties getPropertiesById(int id) {
        return template.queryForObject("select * from " + PROPERTIES_TABLE + " where id=?"
                , new PropertiesRowMapper(), id);
    }

    @Override
    public ParkingLotProperties getPropertiesByName(String name) {
        if (template == null) {
            throw new RuntimeException("template is null!");
        }
        return template.queryForObject("select * from " + PROPERTIES_TABLE + " where name=?"
                , new PropertiesRowMapper(), name);
    }

    @Override
    public Integer saveProperties(ParkingLotProperties properties) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", properties.getName());
        parameters.put("genericSize", properties.getGenericSize());
        parameters.put("electricSize", properties.getElectricSize());

        Number key = insert_property.executeAndReturnKey(parameters);
        LOG.debug(String.format("Generated db key: %s", key));
        return key.intValue();
    }

    @Override
    public void deleteProperties() {
        template.execute("delete from " + PROPERTIES_TABLE);
    }

    private void createTable(String statement) {
        try {
            template.execute(statement);
        }
        catch (Exception ex) {
            Exceptions.handle(ex, "X0Y32");
        }
    }
}
