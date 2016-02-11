package com.dojo.parkinglot.client;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;

public class StudentClientIntegrationTest {
	private ParkingLotClient client;
		
	@Before
	public void beforeEachTest() {
		this.client = new ParkingLotClient(ClientBuilder.newClient().target("http://localhost:8080/parkinglot/webapi/parkingResource/"));
	}
		
	@Test(expected=RuntimeException.class)
	public void PostSignupFailureForExistingUserTest() throws Exception {
		client.postSignup("j2ee", "j2ee", "jersey", "jersey", "12/15/2013", "jersey@gmail.com");	
	}
	
	@Test(expected=Exception.class)
	public void PostSignupInvalidDateTest() throws Exception {
		client.postSignup("jersey", "jersey", "jersey", "jersey", "12-15-2013", "jersey@gmail.com");
	}
	
	@Test(expected=RuntimeException.class)
	public void PostSignupBadRequestTest() throws Exception {
		client.postSignup(null, null, null, null, null, null);
	}
		
	@Test(expected=RuntimeException.class)
	public void PostLoginFailureTest() {
		client.postEntrance("jersey");
	}
	
	@Test(expected=RuntimeException.class)
	public void PostLoginBadRequestTest() {
		client.postEntrance(null);
	}

}
