package com.dojo.parkinglot.domain.car;

import com.dojo.parkinglot.domain.parkingspace.ParkingSpaceTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Component
public class GenericCar implements VehicleInterface {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ParkingSpaceTypeEnum type = ParkingSpaceTypeEnum.GENERIC;
    private String licensePlate;

    public GenericCar() {
    }

    public GenericCar(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public ParkingSpaceTypeEnum getType() {
        return type;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
