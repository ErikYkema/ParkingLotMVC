package com.dojo.parkinglot.model.car;

import com.dojo.parkinglot.model.parkingspace.ParkingSpaceTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Component
public class ElectricCar implements Vehicle {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ParkingSpaceTypeEnum type = ParkingSpaceTypeEnum.ELECTRIC;
    private String licensePlate;

    @Override
    public ParkingSpaceTypeEnum getType() {
        return type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
