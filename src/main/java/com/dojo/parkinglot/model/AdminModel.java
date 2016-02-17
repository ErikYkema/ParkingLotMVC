package com.dojo.parkinglot.model;

import com.dojo.parkinglot.domain.ParkingLotInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class AdminModel {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ParkingLotInterface parkingLot;

    public ParkingLotInterface getParkingLot() {
        LOG.debug(String.format("parkingLot.getParkingSpaceUsages().size(): %s.", parkingLot.getParkingSpaceUsages().size()));
        return parkingLot;
    }

    public AdminModel(ParkingLotInterface parkingLot) {
        this.parkingLot = parkingLot;
    }
}
