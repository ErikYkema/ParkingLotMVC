package com.dojo.parkinglot.domain.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

// TODO align object relations user vehicle and usercard
public class UserCard {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private int id;
    private String licensePlate;
    private String userId;
}
