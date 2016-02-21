package com.dojo.parkinglot.model;

import com.dojo.parkinglot.domain.ParkingLotInterface;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class AdminModel {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private List<VehicleInterface> vehicles;

    private ParkingLotInterface parkingLot;

    public ParkingLotInterface getParkingLot() {
        LOG.debug(String.format("parkingLot.getParkingSpaceUsages().size(): %s.", parkingLot.getParkingSpaceUsages().size()));
        return parkingLot;
    }

    public List<VehicleInterface> getVehicles() {
        return vehicles;
    }

    public AdminModel(ParkingLotInterface parkingLot, List<VehicleInterface> vehicles) {
        this.parkingLot = parkingLot;
        this.vehicles = vehicles;
    }
}
