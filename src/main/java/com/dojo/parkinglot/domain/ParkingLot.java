package com.dojo.parkinglot.domain;


import com.dojo.parkinglot.domain.car.ElectricCar;
import com.dojo.parkinglot.domain.car.GenericCar;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.repository.ParkingLotRepositoryInterface;
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

    public static final String PARKING_LOT_NAME = "DanielDogShed";

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
        parkingLotProperties = repository.getPropertiesByName(PARKING_LOT_NAME);
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

    private Map<VehicleInterface, ParkingSpaceUsage> parkingSpaceUsages = new HashMap<>();
    public Map<VehicleInterface, ParkingSpaceUsage> getParkingSpaceUsages() {
        return parkingSpaceUsages;
    }

    public boolean requestParkingSpace(VehicleInterface vehicle) {
        if (freeSpaceCounter.useSpace(vehicle)) {
            parkingSpaceUsages.put(vehicle, new ParkingSpaceUsage(vehicle));
            return true;
        }
        return false;
    }

}
