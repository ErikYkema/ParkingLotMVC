package com.dojo.parkinglot.resource;

import com.dojo.parkinglot.domain.ParkingLotInterface;
import com.dojo.parkinglot.domain.ParkingLotProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Root resource (exposed at "webapi/myresource" path)
 */
@Path("restResource")
public class RestResource {

    @Autowired
    ParkingLotInterface parkingLot;

    @GET
    @Path("properties")
    @Produces("application/xml")
    public ParkingLotProperties getProperties() {
        return parkingLot.getProperties();
    }


}
