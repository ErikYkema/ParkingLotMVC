package com.dojo.parkinglot.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ParkingLotClient implements ParkingLotClientInterface {
	private WebTarget target;

	public ParkingLotClient(WebTarget target) {
		this.target = target;
	}

//	public Response getSignup() {
//		Response response = target.path("signup").request().get(Response.class);
//		return response;
//	}
//
//	public Response postSignup(String userName, String password,
//			String firstName, String lastName, String dateOfBirth,
//			String emailAddress) throws Exception {
//
//		Form form = new Form().param("userName", userName)
//				.param("password", password).param("firstName", firstName)
//				.param("lastName", lastName).param("dateOfBirth", dateOfBirth)
//				.param("emailAddress", emailAddress);
//		Response response = target.path("signup").request()
//				.post(Entity.form(form));
//
//		if (response.getStatus() == Status.INTERNAL_SERVER_ERROR
//				.getStatusCode()) {
//			throw new Exception();
//		}
//
//		if (response.getStatus() != Status.OK.getStatusCode()) {
//			throw new RuntimeException();
//		}
//
//		return response;
//	}

	public Response getEntrance() {
		Response response = target.path("entranceXXX").request().get(Response.class);

		return response;
	}

	public Response postEntrance(String licensePlate) {
		Form form = new Form().param("licensePlateXXX", licensePlate);

		Response response = target.path("entranceXXX").request()
				.post(Entity.form(form));

		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}

		return response;
	}
}
