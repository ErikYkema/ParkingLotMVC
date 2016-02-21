package com.dojo.parkinglot.dao;

import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.tools.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VehicleDaoImpl implements VehicleDao {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private JdbcTemplate template;
    private SimpleJdbcInsert insert_vehicle;

    private static final String VEHICLES_TABLE = "VEHICLES";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
        insert_vehicle = new SimpleJdbcInsert(dataSource)
                .withTableName(VEHICLES_TABLE)
                .usingColumns("licensePlate", "type")
                .usingGeneratedKeyColumns("id");
    }

    public void setup () {
         createTable("create table " + VEHICLES_TABLE + " (id INT GENERATED ALWAYS AS IDENTITY, licensePlate VARCHAR(40), type VARCHAR(15))");
    }

    public VehicleInterface findByLicensePlate(String licensePlate) {
        VehicleInterface car;
        if (template == null) {
            throw new RuntimeException("template is null!");
        }
        try {
            car = template.queryForObject("select id, licensePlate, type from " + VEHICLES_TABLE + " where licensePlate=?"
                    , new VehicleRowMapper(), licensePlate);
            return car;
        }
        catch (Exception e) {
            return null;
        }
    }

    public Integer saveVehicle(VehicleInterface vehicle) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("licensePlate", vehicle.getLicensePlate());
        parameters.put("type", vehicle.getType().toString());

        Number key = insert_vehicle.executeAndReturnKey(parameters);
        LOG.debug(String.format("Generated db key on saving vehicle: %s", key));
        return key.intValue();
    }

    public void deleteVehicles() {
        template.execute("delete from " + VEHICLES_TABLE);
    }

    private void createTable(String statement) {
        try {
            template.execute(statement);
        }
        catch (Exception ex) {
            Exceptions.handle(ex, "X0Y32");
        }
    }

    public List<VehicleInterface> getVehicles() {
        List<VehicleInterface> vehicles;
        if (template == null) {
            throw new RuntimeException("template is null!");
        }
        try {
            vehicles = template.query("select id, licensePlate, type from " + VEHICLES_TABLE
                    , new VehicleRowMapper());
        }
        catch (Exception e) {
            return null;
        }

        LOG.debug(String.format("Size of list of vehicles from repository: %s", vehicles.size()));
        return vehicles;
    }

}
