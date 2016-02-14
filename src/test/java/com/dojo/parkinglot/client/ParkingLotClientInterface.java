package com.dojo.parkinglot.client;

import javax.ws.rs.core.Response;

public interface ParkingLotClientInterface {

	public Response getEntrance();

	public Response postEntrance(String licensePlate);

	public Response getAdmin();

}