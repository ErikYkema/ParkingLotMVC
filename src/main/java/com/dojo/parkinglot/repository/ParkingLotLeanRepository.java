package com.dojo.parkinglot.repository;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.domain.car.ElectricCar;
import com.dojo.parkinglot.domain.car.GenericCar;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

@Component
public class ParkingLotLeanRepository implements ParkingLotRepositoryInterface {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public ParkingLotLeanRepository() {
        LOG.debug("constructor...");
    }

    public static final String PARKING_LOT_NAME = "DanielDogShed";

    private ParkingLotProperties properties;

    @Autowired
    public void setProperties(ParkingLotProperties properties) {
        this.properties = properties;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        // no datasource needed for lean setup
    }

    @Override
    public void setup() {
        // nothing for lean setup?
    }

    /*
    clears out the current data and reloads the application setup
     */
    public void seed() {

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
        return properties;
    }

    @Override
    public ParkingLotProperties getPropertiesByName(String name) {
        return properties;
    }

    @Override
    public VehicleInterface findByLicensePlate(String licensePlate) {
        VehicleInterface vehicle = null;
        switch (licensePlate) {
            case "BAR":
                break;
            case "BAZ":
                break;
            case "ELEC1":
                vehicle = new ElectricCar(licensePlate);
                break;
            case "ELEC2":
                vehicle = new ElectricCar(licensePlate);
                break;
            case "ELEC3":
                vehicle = new ElectricCar(licensePlate);
                break;
            case "ELEC4":
                vehicle = new ElectricCar(licensePlate);
                break;
            default:
                vehicle = new GenericCar( );
        }
        LOG.debug("Vehicle found: " + (vehicle == null ? "null" : vehicle.getType()));
        return vehicle;
    }
}




