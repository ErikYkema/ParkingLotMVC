package com.dojo.parkinglot.domain.car;

import com.dojo.parkinglot.domain.parkingspace.ParkingSpaceTypeEnum;

public interface VehicleInterface {

    ParkingSpaceTypeEnum getType();

    public String getLicensePlate();

    public void setLicensePlate(String licensePlate);

}
