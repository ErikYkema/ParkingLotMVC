package com.dojo.parkinglot.dao;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertiesRowMapper implements RowMapper<ParkingLotProperties> {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public ParkingLotProperties mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
        ParkingLotProperties properties = new ParkingLotProperties();
        LOG.debug(String.format("Id: %s", resultSet.getInt("id")));
        properties.setId(resultSet.getInt("id"));
        properties.setName(resultSet.getString("name"));
        properties.setGenericSize(resultSet.getInt("genericSize"));
        properties.setElectricSize(resultSet.getInt("electricSize"));
        return properties;
    }
}