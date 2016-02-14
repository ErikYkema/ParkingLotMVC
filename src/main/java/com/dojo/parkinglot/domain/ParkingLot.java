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

    /* attributes */

    public static final String PARKING_LOT_NAME = "DanielDogShed";

    private ParkingLotRepositoryInterface repository;
    private ParkingLotProperties parkingLotProperties;
    private FreeSpaceCounter freeSpaceCounter;
    private Map<VehicleInterface, ParkingSpaceUsage> parkingSpaceUsages = new HashMap<>();

    /* getters */

    public ParkingLotProperties getProperties() {
        return this.parkingLotProperties;
    }

    public Map<VehicleInterface, ParkingSpaceUsage> getParkingSpaceUsages() {
        return parkingSpaceUsages;
    }

    /* constructor */

    @Autowired
    public ParkingLot(ParkingLotRepositoryInterface repository, FreeSpaceCounter freeSpaceCounter) {
        LOG.debug("constructor...");
        this.repository = repository;
        this.freeSpaceCounter = freeSpaceCounter;
        init();
    }


    /* methods */

    public void init() {
        parkingLotProperties = repository.getPropertiesByName(PARKING_LOT_NAME);
        freeSpaceCounter.setFreeSpace(GenericCar.class, parkingLotProperties.getGenericSize());
        freeSpaceCounter.setFreeSpace(ElectricCar.class, parkingLotProperties.getElectricSize());
    }

    public FreeSpaceCounter getFreeSpaceCounter() {
        LOG.debug("getFreeSpaceCounter: " + freeSpaceCounter.getDescription());
        return freeSpaceCounter;
    }


    public boolean requestParkingSpace(VehicleInterface vehicle) {
        LOG.debug("requestParkingSpace");
        if (freeSpaceCounter.useSpace(vehicle)) {
            parkingSpaceUsages.put(vehicle, new ParkingSpaceUsage(vehicle));
            return true;
        }
        return false;
    }

}
