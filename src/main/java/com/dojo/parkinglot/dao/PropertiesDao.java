package com.dojo.parkinglot.dao;

import com.dojo.parkinglot.domain.ParkingLotProperties;

import javax.sql.DataSource;

public interface PropertiesDao {

    void setup();
    void setDataSource(DataSource dataSource);
    ParkingLotProperties getPropertiesById(int id);
    ParkingLotProperties getPropertiesByName(String name);
    Integer saveProperties(ParkingLotProperties properties);
    void deleteProperties();

}
