package com.dojo.parkinglot.service;

import com.dojo.parkinglot.model.StudentInterface;
import com.dojo.parkinglot.model.car.Vehicle;

public interface ParkingService {
	StudentInterface save(StudentInterface student);
	boolean findByLogin(String userName, String password);
	Vehicle findByLicensePlate(String licensePlate);
	boolean getFreeSpace(Vehicle vehicle);
}
