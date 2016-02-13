package com.dojo.parkinglot.model.old;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Component
@XmlRootElement(name="student")
//@Entity
//@Table(name="student")
public class Student implements StudentInterface {
	
//	@Id
//	@GeneratedValue
	private Long id;

	//@Size(min=4, max=20)
	private String userName;

	private String firstName;

	private String lastName;

	//@Size(min=4, max=8)
	private String password;
	

	private String emailAddress;
	
	//@NotNull
	@Past
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date dateOfBirth;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}	
}
