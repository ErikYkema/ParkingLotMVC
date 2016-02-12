package com.dojo.parkinglot.service;

import com.dojo.parkinglot.model.ParkingLotRepositoryInterface;
import com.dojo.parkinglot.model.StudentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studentService")
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	private ParkingLotRepositoryInterface parkingLotRepositoryInterface;

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

	public boolean findByLicensePlate(String licensePlate) {
		boolean found = parkingLotRepositoryInterface.findByLicensePlate(licensePlate);

		return found;
	}
}
