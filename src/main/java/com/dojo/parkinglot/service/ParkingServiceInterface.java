package com.dojo.parkinglot.service;

import com.dojo.parkinglot.domain.ParkingLotInterface;
import com.dojo.parkinglot.domain.ParkingTicket;
import com.dojo.parkinglot.domain.car.VehicleInterface;

/*
The services wraps all access to the domain and logic, for access by the resource/controller.
 */

public interface ParkingServiceInterface {

	VehicleInterface findByLicensePlateFromRepository(String licensePlate);
	boolean getFreeSpace(VehicleInterface vehicle);
	ParkingTicket releaseSpace(String licensePlate);
	ParkingLotInterface getParkingLot();
}
