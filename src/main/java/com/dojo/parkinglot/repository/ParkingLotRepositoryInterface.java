package com.dojo.parkinglot.repository;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collection;

@Component
public interface ParkingLotRepositoryInterface {

    Integer saveProperties(ParkingLotProperties properties);

    Collection<ParkingLotProperties> getAllProperties();

    public void setProperties(ParkingLotProperties properties);

    ParkingLotProperties getPropertiesById(int id);

    ParkingLotProperties getPropertiesByName(String name);

    void setup();

    void seed();

    VehicleInterface findByLicensePlate(String licensePlate);

    public void setDataSource(DataSource dataSource);

}
