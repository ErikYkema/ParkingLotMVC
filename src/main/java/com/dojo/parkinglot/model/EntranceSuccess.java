package com.dojo.parkinglot.model;

import com.dojo.parkinglot.domain.ParkingLot;
import com.dojo.parkinglot.domain.ParkingSpaceUsage;
import com.dojo.parkinglot.domain.car.VehicleInterface;

import java.util.Map;

public class EntranceSuccess {
    private Map<VehicleInterface, ParkingSpaceUsage> usages;
    private VehicleInterface vehicle;
    public Map<VehicleInterface, ParkingSpaceUsage> getUsages() {
        return usages;
    }
    public VehicleInterface getVehicle() {
        return vehicle;
    }



    public EntranceSuccess(ParkingLot parkingLot, VehicleInterface vehicle) {
        this.usages = parkingLot.getParkingSpaceUsages();
        this.vehicle = vehicle;
    }
}
