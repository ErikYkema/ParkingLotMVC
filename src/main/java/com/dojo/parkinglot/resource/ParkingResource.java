package com.dojo.parkinglot.resource;

import com.dojo.parkinglot.model.Student;
import com.dojo.parkinglot.model.car.Vehicle;
import com.dojo.parkinglot.service.ParkingService;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.invoke.MethodHandles;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Component
@Path("parkingResource")
@XmlRootElement
public class ParkingResource implements ParkingResourceInterface {
	private final static Logger LOG =
			LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private ParkingService parkingService;

	@GET
	@Path("signup")
	@Produces(MediaType.TEXT_HTML)
	public Response signup() {
		return Response.ok(new Viewable("/signup")).build();
	}

	@POST
	@Path("signup")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response signup(@FormParam("userName") String userName,
			@FormParam("password") String password,
			@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("dateOfBirth") String dateOfBirth,
			@FormParam("emailAddress") String emailAddress)
			throws ParseException {

		if (userName == null || password == null || firstName == null
				|| lastName == null || dateOfBirth == null
				|| emailAddress == null) {
			return Response.status(Status.PRECONDITION_FAILED).build();
		}

		Student student = new Student();
		student.setUserName(userName);
		student.setPassword(password);
		student.setFirstName(firstName);
		student.setLastName(lastName);

		student.setDateOfBirth(new java.sql.Date(new SimpleDateFormat(
				"MM/dd/yyyy").parse(dateOfBirth.substring(0, 10)).getTime()));

		student.setEmailAddress(emailAddress);

		if (parkingService.findByLicensePlate(userName)!=null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "User Name exists. Try another user name");
			map.put("student", student);
			return Response.status(Status.BAD_REQUEST)
					.entity(new Viewable("/signup", map)).build();
		} else {
			parkingService.save(student);
			return Response.ok().entity(new Viewable("/entrance")).build();
		}
	}

	@GET
	@Path("entrance")
	@Produces(MediaType.TEXT_HTML)
	public Response entrance() {
		return Response.ok(new Viewable("/entrance")).build();
	}

	@POST
	@Path("entrance")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response entrance(@FormParam("licensePlate") String licensePlate) {
		LOG.debug(String.format("licensePLate: '%s'.", licensePlate));
		if (StringUtils.isBlank(licensePlate)) {
			return Response.status(Status.PRECONDITION_FAILED).build();
		}

		Vehicle vehicle = parkingService.findByLicensePlate(licensePlate);
		if (vehicle == null) {
			return Response.status(Status.BAD_REQUEST)
					.entity(new Viewable("/failure")).build();
		}

		boolean space = parkingService.getFreeSpace(vehicle);
		if (space) {
			LOG.debug("success");
			return Response.ok().entity(new Viewable("/success")).build();
		} else {
			LOG.debug("failure");
			return Response.status(Status.BAD_REQUEST)
					.entity(new Viewable("/failure")).build();
		}
	}
}
