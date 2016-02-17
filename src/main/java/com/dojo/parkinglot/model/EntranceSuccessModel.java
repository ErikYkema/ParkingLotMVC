package com.dojo.parkinglot.model;

import com.dojo.parkinglot.domain.car.VehicleInterface;

public class EntranceSuccessModel {
    private VehicleInterface vehicle;
    public VehicleInterface getVehicle() {
        return vehicle;
    }


    public EntranceSuccessModel(VehicleInterface vehicle) {
        this.vehicle = vehicle;
    }
}
