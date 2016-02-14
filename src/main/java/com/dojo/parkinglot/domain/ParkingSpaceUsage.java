package com.dojo.parkinglot.domain;


import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.domain.parkingspace.ParkingSpaceInterface;
import com.dojo.parkinglot.domain.parkingspace.ParkingSpaceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Date;

public class ParkingSpaceUsage {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ParkingSpaceInterface parkingSpace;

    private Date startUsageDateTime;

    public ParkingSpaceUsage(VehicleInterface vehicle) {
        this.parkingSpace = ParkingSpaceFactory.createParkingSpace(vehicle.getType());
        this.startUsageDateTime = new Date();
    }

    public ParkingSpaceInterface getParkingSpace() {
        return parkingSpace;
    }

    public Date getStartUsageDateTime() {
        return startUsageDateTime;
    }

    public Long getParkingDuration() {
        return new Date().getTime() - startUsageDateTime.getTime();
    }
}
