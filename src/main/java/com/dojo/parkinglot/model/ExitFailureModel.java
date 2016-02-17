package com.dojo.parkinglot.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class ExitFailureModel extends EntranceFailureModel {

    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public ExitFailureModel(String licensePlate, String message) {
        super(licensePlate, message);
    }
}
