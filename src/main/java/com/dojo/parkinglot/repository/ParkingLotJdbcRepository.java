package com.dojo.parkinglot.repository;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.domain.car.ElectricCar;
import com.dojo.parkinglot.domain.car.GenericCar;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.domain.parkingspace.ParkingSpaceTypeEnum;
import com.dojo.parkinglot.tools.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Primary
@Component
public class ParkingLotJdbcRepository implements ParkingLotRepositoryInterface {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private JdbcTemplate template;
    private SimpleJdbcInsert insert_property;
    private SimpleJdbcInsert insert_vehicle;

    private static final String PROPERTIES_TABLE = "PARKINGLOTPROPERTIES";
    private static final String VEHICLES_TABLE = "VEHICLES";
    public static final String PARKING_LOT_NAME = "DanielDogShed";


    public ParkingLotJdbcRepository() {
        LOG.debug("constructor...");
    }

    private DataSource dataSource;
    private ParkingLotProperties properties;
// //   private VehicleInterface vehicle;
//
//    @Autowired
//    public void setVehicle(VehicleInterface vehicle) {
//        this.vehicle = vehicle;
//    }

    @Autowired
    public void setProperties(ParkingLotProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        LOG.debug("setDataSource...");
        this.dataSource = dataSource;
    }

    @Override
    public void setup() {
        LOG.debug("setup JDBC environment...");
        template = new JdbcTemplate(dataSource);

        insert_property = new SimpleJdbcInsert(dataSource)
                .withTableName(PROPERTIES_TABLE)
                .usingColumns("name", "genericSize", "electricSize")
                .usingGeneratedKeyColumns("id");

        insert_vehicle = new SimpleJdbcInsert(dataSource)
                .withTableName(VEHICLES_TABLE)
                .usingColumns("licensePlate", "type")
                .usingGeneratedKeyColumns("id");

//        switch (feature) {
//            case DROP_AND_CREATE:
//                try {
//
//                    //template.execute("drop table " + PROPERTIES_TABLE);
//                } catch (Exception ex) {
//                    //TODO avoid error in first usage
//                   // Exceptions.handle(ex, "X0Y32");
//                }
//                break;
//            default:
//                break;
//        }

        createTable("create table " + PROPERTIES_TABLE + " (id INT GENERATED ALWAYS AS IDENTITY, name VARCHAR(40), genericSize INT, electricSize INT)");
        createTable("create table " + VEHICLES_TABLE + " (id INT GENERATED ALWAYS AS IDENTITY, licensePlate VARCHAR(40), type VARCHAR(15))");
    }

    private void createTable(String statement) {
        try {
            template.execute(statement);
        }
        catch (Exception ex) {
           //TODO avoid error in first usage
           Exceptions.handle(ex, "X0Y32");
        }
    }

    /*
    clears out the current data and reloads the application setup
     */
    public void seed() {
        template.execute("delete from " + PROPERTIES_TABLE);
        template.execute("delete from " + VEHICLES_TABLE);

        properties.setName(PARKING_LOT_NAME);
        properties.setGenericSize(11);
        properties.setElectricSize(3);
        properties.setId(saveProperties(properties));

        saveVehicle(new ElectricCar("ELEC1"));
        saveVehicle(new ElectricCar("ELEC2"));
        saveVehicle(new ElectricCar("ELEC3"));
        saveVehicle(new GenericCar("GENERIC"));
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

    public Integer saveVehicle(VehicleInterface vehicle) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("licensePlate", vehicle.getLicensePlate());
        parameters.put("type", vehicle.getType().toString());

        Number key = insert_vehicle.executeAndReturnKey(parameters);
        LOG.debug(String.format("Generated db key on saving vehicle: %s", key));
        return key.intValue();
    }

    @Override
    public Collection<ParkingLotProperties> getAllProperties() {
        // TODO implement, as we may not know what the ID is
        return null;
    }

    class PropertiesRowMapper implements RowMapper<ParkingLotProperties> {

        @Override
        public ParkingLotProperties mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
            ParkingLotProperties properties = new ParkingLotProperties();
            LOG.debug(String.format("Id: %s", resultSet.getInt("id")));
            properties.setId(resultSet.getInt("id"));
            properties.setName(resultSet.getString("name"));
            properties.setGenericSize(resultSet.getInt("genericSize"));
            properties.setElectricSize(resultSet.getInt("electricSize"));
            return properties;
        }
    }

    class VehicleRowMapper implements RowMapper<VehicleInterface> {

        @Override
        public VehicleInterface mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
            VehicleInterface vehicle;
            if (resultSet.getString("type").equals(ParkingSpaceTypeEnum.GENERIC.toString())) {
                vehicle = new GenericCar();
            } else if (resultSet.getString("type").equals(ParkingSpaceTypeEnum.ELECTRIC.toString())) {
                vehicle = new ElectricCar();
            }
            else return null;
            LOG.debug(String.format("Found vehicle Id: %s", resultSet.getInt("id")));
            vehicle.setId(resultSet.getInt("id"));
            vehicle.setLicensePlate(resultSet.getString("licensePlate"));
            return vehicle;
        }
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

    public VehicleInterface findByLicensePlate(String licensePlate) {

        if (template == null) {
            throw new RuntimeException("template is null!");
        }
        return template.queryForObject("select id, licensePlate, type from " + VEHICLES_TABLE + " where licensePlate=?"
                , new VehicleRowMapper(), licensePlate);
    }

    public VehicleInterface getVehicleById(int id) {
        if (template == null) {
            throw new RuntimeException("template is null!");
        }
        return template.queryForObject("select id, licensePlate, type from " + VEHICLES_TABLE + " where id=?"
                , new VehicleRowMapper(), id);    }

}




