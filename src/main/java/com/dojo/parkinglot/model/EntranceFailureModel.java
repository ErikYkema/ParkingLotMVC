package com.dojo.parkinglot.model;

import com.dojo.parkinglot.domain.car.VehicleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class EntranceFailureModel {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    VehicleInterface vehicle;
    String message;

    public VehicleInterface getVehicle() {
        return vehicle;
    }

    public String getMessage() {
        return message;
    }

    public EntranceFailureModel(VehicleInterface vehicle, String message) {
        LOG.warn(message);
        this.message = message;
        this.vehicle = vehicle;
    }
}
