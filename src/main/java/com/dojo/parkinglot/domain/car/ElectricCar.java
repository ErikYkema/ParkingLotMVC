package com.dojo.parkinglot.domain.car;

import com.dojo.parkinglot.domain.parkingspace.ParkingSpaceTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Component
public class ElectricCar implements VehicleInterface {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ParkingSpaceTypeEnum type = ParkingSpaceTypeEnum.ELECTRIC;
    private String licensePlate;
    private int id;

    public ElectricCar(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public ElectricCar() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
