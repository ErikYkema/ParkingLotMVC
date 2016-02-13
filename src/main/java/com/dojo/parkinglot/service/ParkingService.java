package com.dojo.parkinglot.service;

import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.model.old.StudentInterface;

public interface ParkingService {
	@Deprecated
	StudentInterface save(StudentInterface student);
	@Deprecated
	boolean findByLogin(String userName, String password);
	VehicleInterface findByLicensePlate(String licensePlate);
	boolean getFreeSpace(VehicleInterface vehicle);
}
