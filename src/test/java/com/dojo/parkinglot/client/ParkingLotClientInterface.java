package com.dojo.parkinglot.client;

import javax.ws.rs.core.Response;

public interface ParkingLotClientInterface {

//	public Response getSignup();
//
//	public Response postSignup(String userName, String password,
//			String firstName, String lastName, String dateOfBirth,
//			String emailAddress) throws Exception;

	public Response getEntrance();

	public Response postEntrance(String licensePlate);

}