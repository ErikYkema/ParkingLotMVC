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

@Service("parkingService")
public class ParkingServiceImpl implements ParkingServiceInterface {

	private final static Logger LOG =
			LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private ParkingLotRepositoryInterface repository;
	ParkingLotInterface parkingLot;

	@Autowired
	public ParkingServiceImpl(ParkingLotInterface parkingLot, ParkingLotRepositoryInterface repository) {
		this.parkingLot = parkingLot;
		this.repository = repository;
		repository.seed();
		parkingLot.init(); // TODO why doesn't it work from the autowired parlingLot constructor?
	}

	public ParkingLotInterface getParkingLot() {
		return parkingLot;
	}

	public VehicleInterface findByLicensePlateFromRepository(String licensePlate) {
		VehicleInterface vehicle = repository.findByLicensePlate(licensePlate);
		LOG.debug(String.format("VehicleInterface: %s", vehicle == null ? "" : vehicle));
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
}
