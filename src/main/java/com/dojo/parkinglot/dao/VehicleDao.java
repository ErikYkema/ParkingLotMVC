package com.dojo.parkinglot.dao;

import com.dojo.parkinglot.domain.car.VehicleInterface;

import javax.sql.DataSource;

public interface VehicleDao {

    void setup();
    void setDataSource(DataSource dataSource);
    VehicleInterface findByLicensePlate(String licensePlate);
    Integer saveVehicle(VehicleInterface vehicle);
    void deleteVehicles();
}
