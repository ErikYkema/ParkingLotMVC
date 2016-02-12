package com.dojo.parkinglot.model.user;

import com.dojo.parkinglot.model.BuildingsEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class User {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private int id;
    private String name;
    private BuildingsEnum building;
}
