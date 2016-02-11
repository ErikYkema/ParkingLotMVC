package com.dojo.parkinglot.service;

import com.dojo.parkinglot.model.StudentInterface;
import com.dojo.parkinglot.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service("studentService")
public class ParkingServiceImpl implements ParkingService {

	//@Autowired
	private StudentRepository studentRepository;
	
	public StudentInterface save(StudentInterface student) {
		//return studentRepository.save((Student)student);
		return null;
	}

	public boolean findByLogin(String userName, String password) {	
		StudentInterface stud = studentRepository.findByUserName(userName);
		
		if(stud != null && stud.getPassword().equals(password)) {
			return true;
		} 
		
		return false;		
	}

	public boolean findByLicensePlate(String licensePlate) {
		StudentInterface stud = studentRepository.findByUserName(licensePlate);
		
		if(stud != null) {
			return true;
		}
		
		return false;
	}
}
