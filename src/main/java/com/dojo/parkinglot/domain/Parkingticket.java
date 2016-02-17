package com.dojo.parkinglot.domain;

import com.dojo.parkinglot.domain.car.VehicleInterface;

public class ParkingTicket {
    private VehicleInterface vehicle;
    private double parkingCost;
    private double chargingCost;
    private double duration;

    public ParkingTicket(VehicleInterface vehicle, double parkingCost, double chargingCost, double duration) {
        this.vehicle = vehicle;
        this.parkingCost = parkingCost;
        this.chargingCost = chargingCost;
        this.duration = duration;
    }

    public VehicleInterface getVehicle() {
        return vehicle;
    }

    public double getParkingCost() {
        return parkingCost;
    }

    public double getChargingCost() {
        return chargingCost;
    }

    public double getDuration() {
        return duration;
    }
}
