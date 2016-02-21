package com.dojo.parkinglot.dao;

import com.dojo.parkinglot.domain.car.ElectricCar;
import com.dojo.parkinglot.domain.car.GenericCar;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.domain.parkingspace.ParkingSpaceTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleRowMapper implements RowMapper<VehicleInterface> {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public VehicleInterface mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
        VehicleInterface vehicle;
        if (resultSet.getString("type").equals(ParkingSpaceTypeEnum.GENERIC.toString())) {
            vehicle = new GenericCar();
        } else if (resultSet.getString("type").equals(ParkingSpaceTypeEnum.ELECTRIC.toString())) {
            vehicle = new ElectricCar();
        }
        else {
            throw new RuntimeException("unsupported vehicle type");
        }
        LOG.debug(String.format("Found vehicle Id: %s", resultSet.getInt("id")));
        vehicle.setId(resultSet.getInt("id"));
        vehicle.setLicensePlate(resultSet.getString("licensePlate"));
        return vehicle;
    }
}