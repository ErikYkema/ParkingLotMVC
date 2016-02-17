package com.dojo.parkinglot.resource;

import javax.ws.rs.core.Response;

public interface ParkingResourceInterface {

	// GET
	public Response entrance();

	// POST
	public Response entrance(String licensePlate);

	// GET
	public Response admin();

	// GET
	public Response exit();

	// POST
	public Response exit(String licensePlate);

}