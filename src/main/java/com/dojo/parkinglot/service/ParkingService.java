package com.dojo.parkinglot.service;

import com.dojo.parkinglot.model.StudentInterface;

public interface ParkingService {
	StudentInterface save(StudentInterface student);
	boolean findByLogin(String userName, String password);
	boolean findByLicensePlate(String licensePlate);
}
