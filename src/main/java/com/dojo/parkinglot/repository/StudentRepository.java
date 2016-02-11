package com.dojo.parkinglot.repository;

import com.dojo.parkinglot.model.StudentInterface;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public interface StudentRepository <Student, Long> { // extends JpaRepository
	
	//@Query("select s from Student s where s.userName = :userName")
	StudentInterface findByUserName(String userName);
	
}
