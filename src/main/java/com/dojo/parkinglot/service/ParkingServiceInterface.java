package com.dojo.parkinglot.service;

import com.dojo.parkinglot.domain.ParkingLot;
import com.dojo.parkinglot.domain.car.VehicleInterface;

/*
The services wraps all access to the domain and logic, for access by the resource/controller.
 */

public interface ParkingServiceInterface {

	VehicleInterface findByLicensePlate(String licensePlate);
	boolean getFreeSpace(VehicleInterface vehicle);
	ParkingLot getParkingLot();
}
