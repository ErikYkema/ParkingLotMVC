package com.dojo.parkinglot.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class EntranceFailureModel {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    String licensePlate;
    String message;

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMessage() {
        return message;
    }

    public EntranceFailureModel(String licensePlate, String message) {
        LOG.warn(message);
        this.message = message;
        this.licensePlate = licensePlate;
    }
}
