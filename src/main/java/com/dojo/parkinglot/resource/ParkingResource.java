package com.dojo.parkinglot.resource;

import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.model.AdminModel;
import com.dojo.parkinglot.model.EntranceFailureModel;
import com.dojo.parkinglot.model.EntranceSuccessModel;
import com.dojo.parkinglot.model.old.EntranceModel;
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

	@Autowired
	private ParkingServiceInterface parkingService;



	@GET
	@Path("entrance")
	@Produces(MediaType.TEXT_HTML)
	public Response entrance() {
		EntranceModel model = new EntranceModel(parkingService.getParkingLot().getFreeSpaceCounter());
		return Response.ok(new Viewable("/entrance", model)).build();
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

		VehicleInterface vehicle = parkingService.findByLicensePlate(licensePlate);
		if (vehicle == null) {
			EntranceFailureModel model = new EntranceFailureModel(vehicle, "car with this license plate is unknown..!");
			return Response.status(Status.BAD_REQUEST)
					.entity(new Viewable("/failure", model)).build();
		}

		if (parkingService.getParkingLot().isParked(vehicle.getLicensePlate())) {
			EntranceFailureModel model = new EntranceFailureModel(vehicle, "this license plate has already claimed a spot..!");
			return Response.status(Status.BAD_REQUEST)
					.entity(new Viewable("/failure", model)).build();
		}

		boolean space = parkingService.getFreeSpace(vehicle);
		if (space) {
			EntranceSuccessModel model = new EntranceSuccessModel(parkingService.getParkingLot(), vehicle);
			return Response.ok().entity(new Viewable("/success", model)).build();
		} else {
			EntranceFailureModel model = new EntranceFailureModel(vehicle, "there is no space left for this car type..!");
			return Response.status(Status.BAD_REQUEST)
					.entity(new Viewable("/failure", model)).build();
		}
	}

	@GET
	@Path("admin")
	@Produces(MediaType.TEXT_HTML)
	public Response admin() {
		AdminModel model = new AdminModel(parkingService.getParkingLot());
		return Response.ok(new Viewable("/admin", model)).build();
	}
}
