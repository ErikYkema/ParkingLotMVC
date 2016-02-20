package com.dojo.parkinglot.repository;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import org.springframework.stereotype.Component;

@Component
public interface ParkingLotRepositoryInterface {

    void setup();
    ParkingLotProperties getPropertiesByName(String name);
    ParkingLotProperties getPropertiesById(int Id);
    Integer saveProperties(ParkingLotProperties properties);
    VehicleInterface findByLicensePlate(String licensePlate);

}
