package com.dojo.parkinglot.domain.car;

import com.dojo.parkinglot.domain.parkingspace.ParkingSpaceTypeEnum;

public interface VehicleInterface {

    //TODO type is overlapping implementing class type, replace?
    ParkingSpaceTypeEnum getType();

    public String getLicensePlate();

    public void setLicensePlate(String licensePlate);

}
