package com.dojo.parkinglot.resource;

import javax.ws.rs.core.Response;
import java.text.ParseException;

public interface ParkingResourceInterface {

	@Deprecated
	public Response signup();

	@Deprecated
	public Response signup(String userName, String password, String firstName,
			String lastName, String dateOfBirth, String emailAddress)
			throws ParseException;

	// GET
	public Response entrance();

	// POST
	public Response entrance(String licensePlate);

}