package com.dojo.parkinglot.model;

import com.dojo.parkinglot.model.car.Vehicle;
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

    Vehicle findByLicensePlate(String licensePlate);

}
