package com.dojo.parkinglot.service;

import com.dojo.parkinglot.domain.ParkingLotInterface;
import com.dojo.parkinglot.domain.ParkingTicket;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.repository.ParkingLotRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service("parkingService")
public class ParkingServiceImpl implements ParkingServiceInterface {

	private final static Logger LOG =
			LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private ParkingLotRepositoryInterface repository;

	@Autowired
	ParkingLotInterface parkingLot;

	public ParkingLotInterface getParkingLot() {
		return parkingLot;
	}

	public VehicleInterface findByLicensePlateFromRepository(String licensePlate) {
		VehicleInterface vehicle = repository.findByLicensePlate(licensePlate);
		LOG.debug(String.format("VehicleInterface: %s", vehicle == null ? "Geen vehicle gevonden" : vehicle));
		return vehicle;
	}

	/*
	checks for, and if available, claims a space in the parking lot and stars a usage record.
	 */
	public boolean getFreeSpace(VehicleInterface vehicle) {
		boolean space = parkingLot.requestParkingSpace(vehicle);
		LOG.debug(space ? "found" : "not found");
		return space;
	}

	public ParkingTicket releaseSpace(String licensePlate) {
		ParkingTicket ticket = parkingLot.releaseParkingSpace(licensePlate);
		return ticket;
	}

	public List<VehicleInterface> getVehicles() {
		return repository.getVehicles();
	}

}
