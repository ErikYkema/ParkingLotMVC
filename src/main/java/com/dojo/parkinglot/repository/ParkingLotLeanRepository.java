package com.dojo.parkinglot.repository;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.domain.car.ElectricCar;
import com.dojo.parkinglot.domain.car.GenericCar;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Component
public class ParkingLotLeanRepository implements ParkingLotRepositoryInterface {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public ParkingLotLeanRepository() {
        LOG.debug("constructor...");
    }

    public static final String PARKING_LOT_NAME = "DanielDogShed";

    private ParkingLotProperties properties;

    @Override
    public void setup() {
        properties = new ParkingLotProperties();
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




