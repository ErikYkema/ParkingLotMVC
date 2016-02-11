package com.dojo.parkinglot.resource;

import javax.ws.rs.core.Response;
import java.text.ParseException;

public interface ParkingResourceInterface {

	public Response signup();

	public Response signup(String userName, String password, String firstName,
			String lastName, String dateOfBirth, String emailAddress)
			throws ParseException;

	public Response entrance();

	public Response entrance(String licensePlate);

}