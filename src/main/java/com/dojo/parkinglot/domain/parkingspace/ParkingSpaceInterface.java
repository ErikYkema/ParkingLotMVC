package com.dojo.parkinglot.domain.parkingspace;

import com.dojo.parkinglot.domain.car.VehicleInterface;

public interface ParkingSpaceInterface {

    ParkingSpaceTypeEnum getType();

    void parkVehicle(VehicleInterface vehicle);

}
