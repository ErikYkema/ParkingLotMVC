package com.dojo.parkinglot.model.parkingspace;

import com.dojo.parkinglot.model.car.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Component
public class RegularParkingSpace implements ParkingSpace {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public RegularParkingSpace() {

    }

    @Override
    public void parkVehicle(Vehicle car) {
        // wat is nodig om een reguliere auto te parkeren

    }

}
