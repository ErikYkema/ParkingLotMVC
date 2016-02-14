package com.dojo.parkinglot.repository;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface ParkingLotRepositoryInterface {

    public enum Feature {
        DROP_AND_CREATE,
        CREATE_TABLES_IF_NEEDED;
    }

    Integer saveProperties(ParkingLotProperties properties);

    Collection<ParkingLotProperties> getAllProperties();

    ParkingLotProperties getPropertiesById(int id);

    ParkingLotProperties getPropertiesByName(String name);

    void setup(Feature feature);

    void seed();

    VehicleInterface findByLicensePlate(String licensePlate);

}