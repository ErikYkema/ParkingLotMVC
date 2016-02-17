package com.dojo.parkinglot.domain;

import com.dojo.parkinglot.domain.car.VehicleInterface;

import java.util.Map;

public interface ParkingLotInterface {
    ParkingLotProperties getProperties();

    Map<VehicleInterface, ParkingSpaceUsage> getParkingSpaceUsages();

    void init();

    FreeSpaceCounter getFreeSpaceCounter();

    boolean requestParkingSpace(VehicleInterface vehicle);

    ParkingTicket releaseParkingSpace(String licensePlate);

    boolean isParked(String licensePlate);
}
