package com.dojo.parkinglot.resource;

import com.dojo.parkinglot.domain.ParkingTicket;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.model.*;
import com.dojo.parkinglot.service.ParkingServiceInterface;
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

@Component
@Path("parkingResource")
@XmlRootElement
public class ParkingResource implements ParkingResourceInterface {
	private final static Logger LOG =
			LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private String getTemplate (String resource) {
		return "/" + resource;
	}

	private String getSuccessTemplate (String resource) {
		return "/" + resource + "Success";
	}

	private String getFailureTemplate (String resource) {
		return "/" + resource + "Failure";
	}

	private static final String ADMIN_RESOURCE = "admin";
	private static final String ENTRANCE_RESOURCE = "entrance";
	private static final String EXIT_RESOURCE = "exit";

	@Autowired
	private ParkingServiceInterface parkingService;


	@GET
	@Path(ENTRANCE_RESOURCE)
	@Produces(MediaType.TEXT_HTML)
	public Response entrance() {
		EntranceModel model = new EntranceModel(parkingService.getParkingLot().getFreeSpaceCounter());
		return Response.ok(new Viewable(getTemplate(ENTRANCE_RESOURCE), model)).build();
	}

	@POST
	@Path(ENTRANCE_RESOURCE)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response entrance(@FormParam("licensePlate") String licensePlate) {
		LOG.debug(String.format("licensePLate: '%s'.", licensePlate));
		if (StringUtils.isBlank(licensePlate)) {
			return Response.status(Status.PRECONDITION_FAILED).build();
		}

		VehicleInterface vehicle = parkingService.findByLicensePlateFromRepository(licensePlate);
		if (vehicle == null) {
			EntranceFailureModel model = new EntranceFailureModel(licensePlate, "car with this license plate is unknown..!");
			return Response.status(Status.BAD_REQUEST)
					.entity(new Viewable(getFailureTemplate(ENTRANCE_RESOURCE), model)).build();
		}

		if (parkingService.getParkingLot().isParked(vehicle.getLicensePlate())) {
			EntranceFailureModel model = new EntranceFailureModel(licensePlate, "this license plate has already claimed a spot..!");
			return Response.status(Status.BAD_REQUEST)
					.entity(new Viewable(getFailureTemplate(ENTRANCE_RESOURCE), model)).build();
		}

		boolean space = parkingService.getFreeSpace(vehicle);
		if (space) {
			EntranceSuccessModel model = new EntranceSuccessModel(vehicle);
			return Response.ok().entity(new Viewable(getSuccessTemplate(ENTRANCE_RESOURCE), model)).build();
		} else {
			EntranceFailureModel model = new EntranceFailureModel(licensePlate, "there is no space left for this car type..!");
			return Response.status(Status.BAD_REQUEST)
					.entity(new Viewable(getFailureTemplate(ENTRANCE_RESOURCE), model)).build();
		}
	}

	@GET
	@Path(ADMIN_RESOURCE)
	@Produces(MediaType.TEXT_HTML)
	public Response admin() {
		AdminModel model = new AdminModel(parkingService.getParkingLot());
		return Response.ok(new Viewable(getTemplate(ADMIN_RESOURCE), model)).build();
	}

	@GET
	@Path(EXIT_RESOURCE)
	@Produces(MediaType.TEXT_HTML)
	public Response exit() {
		return Response.ok(new Viewable(getTemplate(EXIT_RESOURCE))).build();
	}

	@POST
	@Path(EXIT_RESOURCE)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response exit(@FormParam("licensePlate") String licensePlate) {
		LOG.debug(String.format("licensePLate: '%s'.", licensePlate));
		if (StringUtils.isBlank(licensePlate)) {
			return Response.status(Status.PRECONDITION_FAILED).build();
		}

		if (!parkingService.getParkingLot().isParked(licensePlate)) {
			ExitFailureModel model = new ExitFailureModel(licensePlate, "this vehicle is not parked here!");
			return Response.status(Status.BAD_REQUEST)
					.entity(new Viewable(getFailureTemplate(EXIT_RESOURCE), model)).build();
		}

		try {
			ParkingTicket ticket = parkingService.releaseSpace(licensePlate);
			ExitSuccessModel model = new ExitSuccessModel(ticket);
			return Response.ok().entity(new Viewable(getSuccessTemplate(EXIT_RESOURCE), model)).build();
		} catch (Exception ex) {
			ExitFailureModel model = new ExitFailureModel(licensePlate, "error processing exit request..." + ex.getClass());
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new Viewable(getFailureTemplate(EXIT_RESOURCE), model)).build();
		}

	}

}
