package com.dojo.parkinglot.domain.car;

import com.dojo.parkinglot.domain.parkingspace.ParkingSpaceTypeEnum;

public interface VehicleInterface {

    ParkingSpaceTypeEnum getType();

    String getLicensePlate();

    void setLicensePlate(String licensePlate);

    void setId (int id);

    int getId();

}
