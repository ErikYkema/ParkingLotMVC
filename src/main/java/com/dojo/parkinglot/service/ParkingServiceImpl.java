package com.dojo.parkinglot.service;

import com.dojo.parkinglot.domain.ParkingLot;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.repository.ParkingLotRepositoryInterface;
import com.dojo.parkinglot.model.old.StudentInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service("parkingService")
public class ParkingServiceImpl implements ParkingService {

	private final static Logger LOG =
			LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private ParkingLotRepositoryInterface parkingLotRepositoryInterface;

	@Autowired
	ParkingLot parkingLot;

	@Autowired
	public ParkingServiceImpl(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
		parkingLot.init();
	}

	@Deprecated
	public StudentInterface save(StudentInterface student) {
		//return parkingLotRepository.save((Student)student);
		return null;
	}

	@Deprecated
	public boolean findByLogin(String userName, String password) {	
//		StudentInterface stud = parkingLotRepository.findByUserName(userName);
//
//		if(stud != null && stud.getPassword().equals(password)) {
//			return true;
//		}
		
		return false;		
	}

	public VehicleInterface findByLicensePlate(String licensePlate) {
		VehicleInterface vehicle = parkingLotRepositoryInterface.findByLicensePlate(licensePlate);
		LOG.debug(String.format("VehicleInterface: %s", vehicle));
		return vehicle;
	}

	public boolean getFreeSpace(VehicleInterface vehicle) {
		boolean space = parkingLot.requestParkingSpace(vehicle);
		LOG.debug(space ? "found" : "not found");
		return space;
	}
}
