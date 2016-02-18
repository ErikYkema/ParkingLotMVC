package com.dojo.parkinglot.domain;


import com.dojo.parkinglot.domain.car.ElectricCar;
import com.dojo.parkinglot.domain.car.GenericCar;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.domain.parkingspace.ParkingSpaceTypeEnum;
import com.dojo.parkinglot.repository.ParkingLotRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

@Component
public class ParkingLot implements ParkingLotInterface {

    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /* attributes */

    public static final String PARKING_LOT_NAME = "DanielDogShed";

    private ParkingLotRepositoryInterface repository;
    private ParkingLotProperties parkingLotProperties;
    private FreeSpaceCounter freeSpaceCounter;
    private Map<VehicleInterface, ParkingSpaceUsage> parkingSpaceUsages = new HashMap<>();

    /* getters */

    @Override
    public ParkingLotProperties getProperties() {
        return this.parkingLotProperties;
    }

    @Override
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

    @Override
    public void init() {
        repository.setup();
        parkingLotProperties = repository.getPropertiesByName(PARKING_LOT_NAME);
        parkingLotProperties.setChargingRate(9.90);
        parkingLotProperties.setParkingRate(0.50);
        freeSpaceCounter.setFreeSpace(GenericCar.class, parkingLotProperties.getGenericSize());
        freeSpaceCounter.setFreeSpace(ElectricCar.class, parkingLotProperties.getElectricSize());
    }

    @Override
    public FreeSpaceCounter getFreeSpaceCounter() {
        LOG.debug("getFreeSpaceCounter: " + freeSpaceCounter.getDescription());
        return freeSpaceCounter;
    }


    @Override
    public boolean requestParkingSpace(VehicleInterface vehicle) {
        LOG.debug(String.format("requestParkingSpace for license plate %s and vehicle type %s."
                , vehicle.getLicensePlate(), vehicle.getType()));
        if (freeSpaceCounter.useSpace(vehicle)) {
            parkingSpaceUsages.put(vehicle, new ParkingSpaceUsage(vehicle));
            return true;
        }
        return false;
    }

    @Override
    public ParkingTicket releaseParkingSpace(String licensePlate) {
        // check that car is indeed parked
        if (! isParked(licensePlate)) {
            LOG.warn("car is not parked here!");
            throw new IllegalArgumentException("car is not parked here!");
        }

        // and that we know its type
        VehicleInterface vehicle = repository.findByLicensePlate(licensePlate);
        if ( vehicle == null ) {
            throw new IllegalArgumentException("car is not known here!");
        }

        // remove from the list of usages
        ParkingSpaceUsage parkingSpaceUsage = deleteUsage(licensePlate);
        double duration = parkingSpaceUsage.getParkingDuration();

        // bump up the freespace counter
        freeSpaceCounter.release(vehicle);

        // create ticket
        return new ParkingTicket (
                vehicle
                , getParkingCost(duration/60)
                , getChargingCost(vehicle, duration/60)
                , duration
                );
    }

    private double getParkingCost(double duration) {
        return duration * parkingLotProperties.getParkingRate();
    }

    private double getChargingCost(VehicleInterface vehicle, double duration) {
        return vehicle.getType() == ParkingSpaceTypeEnum.ELECTRIC
                ? duration * parkingLotProperties.getChargingRate()
                : 0;
    }

    @Override
    public boolean isParked(String licensePlate) {
        boolean found = false;
        for (VehicleInterface vehicle : parkingSpaceUsages.keySet()) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                found = true;
                break;
            }
        }
        return found;
    }

    private ParkingSpaceUsage deleteUsage(String licensePlate) {
        ParkingSpaceUsage result = null;
        for (VehicleInterface vehicle : parkingSpaceUsages.keySet()) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                result = parkingSpaceUsages.remove(vehicle);
                break;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException(String.format("This licenseplate %s is not in the map!", licensePlate));
        }
        return result;
    }

}
