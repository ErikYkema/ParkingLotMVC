package com.dojo.parkinglot.domain.parkingspace;

import com.dojo.parkinglot.domain.car.VehicleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Component
public class ElectricParkingSpace extends RegularParkingSpace {

    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public ElectricParkingSpace() {
    }

    @Override
    public void parkVehicle(VehicleInterface eCar) {
        // wat is nodig om een electrische auto te parkeren
    }
}
