package com.dojo.parkinglot.repository;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.domain.car.ElectricCar;
import com.dojo.parkinglot.domain.car.GenericCar;
import com.dojo.parkinglot.domain.car.VehicleInterface;
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
import java.util.Date;

@Primary
@Component("parkingLotRepository")
public class ParkingLotLeanRepository implements ParkingLotRepositoryInterface {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private JdbcTemplate template;
    private SimpleJdbcInsert insert;

    private static final String PROPERTIES_TABLE = "PARKINGLOTPROPERTIES";
    public static final String PARKING_LOT_NAME = "DanielDogShed";

    public ParkingLotLeanRepository() {
        LOG.debug("constructor...");
    }

    @Autowired
    ParkingLotProperties properties;

    //@Autowired
    public void setDataSource(DataSource dataSources) {
        LOG.debug("setDataSource...");

        template = new JdbcTemplate(dataSources);

        insert = new SimpleJdbcInsert(dataSources)
                .withTableName(PROPERTIES_TABLE)
                .usingColumns("name", "genericSize", "electricSize")
                .usingGeneratedKeyColumns("id");

    }

    public void setup(Feature feature) {
        switch (feature) {
            case DROP_AND_CREATE:
                try {
                    template.execute("drop table " + PROPERTIES_TABLE);
                } catch (Exception ex) {
                    Exceptions.handle(ex, "X0Y32");
                }
                break;
            default:
                break;
        }

        try {
            template.execute("create table " + PROPERTIES_TABLE + " (id INT GENERATED ALWAYS AS IDENTITY, name VARCHAR(40), genericSize INT, electricSize INT)");
        } catch (Exception ex) {
            Exceptions.handle(ex, "X0Y32");
        }
    }

    /*
    clears out the current data and reloads the application setup
     */
    public void seed() {
        //template.execute("delete from " + PROPERTIES_TABLE);

        properties.setName(PARKING_LOT_NAME);
        properties.setGenericSize(11);
        properties.setElectricSize(3);
        properties.setId(saveProperties(properties));
    }



    @Override
    public Integer saveProperties(ParkingLotProperties properties) {
        this.properties = properties;
        return 1;
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

    @Override
    public ParkingLotProperties getPropertiesById(int id) {
        return template.queryForObject("select * from " + PROPERTIES_TABLE + " where id=?"
                , new PropertiesRowMapper(), id);
    }

    @Override
    public ParkingLotProperties getPropertiesByName(String name) {
        seed();
        return properties;
    }

    @Override
    public VehicleInterface findByLicensePlate(String licensePlate) {
        switch (licensePlate) {
            case "BAR":
            return null;
            case "BAZ":
                return null;
            case "ELEC":
                return new ElectricCar(licensePlate + new Date().hashCode());
            default:
                return new GenericCar(licensePlate);
        }
    }
}




