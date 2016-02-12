package com.dojo.parkinglot.model;


import com.dojo.parkinglot.model.car.ElectricCar;
import com.dojo.parkinglot.model.car.GenericCar;
import com.dojo.parkinglot.model.car.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

@Component
public class ParkingLot {

    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    ParkingLotRepositoryInterface repository;

    private ParkingLotProperties parkingLotProperties;

    // singleton pattern implementation
//    private static ParkingLot parkingLot;
//    public static ParkingLot getParkingLot() {
//        return this;
//    }
//        if (parkingLot == null) {
//            parkingLot = new ParkingLot();
//        }
//        return parkingLot;
//    }



    @Autowired
    public ParkingLot(ParkingLotRepositoryInterface repository) {
        LOG.debug("constructor...");
        this.repository = repository;
    }

    /*
    fetch the properties from the database
     */
    public void init() {
        parkingLotProperties = repository.getPropertiesByName(ParkingLotJdbcRepository.PARKING_LOT_NAME);
        freeSpaceCounter.setFreeSpace(GenericCar.class, parkingLotProperties.getGenericSize());
        freeSpaceCounter.setFreeSpace(ElectricCar.class, parkingLotProperties.getElectricSize());
    }

    public ParkingLotProperties getProperties() {
        return this.parkingLotProperties;
    }

    @Autowired
    private FreeSpaceCounter freeSpaceCounter; // = new FreeSpaceCounter();
    public FreeSpaceCounter getFreeSpaceCounter() {
        return freeSpaceCounter;
    }

    private Map<Vehicle, ParkingSpaceUsage> parkingSpaceUsages = new HashMap<>();
    public Map<Vehicle, ParkingSpaceUsage> getParkingSpaceUsages() {
        return parkingSpaceUsages;
    }

    public boolean requestParkingSpace(Vehicle vehicle) {
        if (freeSpaceCounter.useSpace(vehicle)) {
            parkingSpaceUsages.put(vehicle, new ParkingSpaceUsage(vehicle));
            return true;
        }
        return false;
    }

}
