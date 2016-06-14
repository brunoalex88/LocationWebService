package com.locationwebservice.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.locationwebservice.controller.LocationController;
import com.locationwebservice.model.Location;

@Path("/location")
public class LocationResource {
	
	@POST
	@Path("/insert/{location}")
	@Consumes("application/json")
	@Produces("text/plain")
	public int insertLocation(@PathParam("location") Location location) {
		return new LocationController().insertLocation(location);
	}
	
	@GET
	@Path("/getLocations/{deviceId}")
	@Produces("application/json")
	public ArrayList<Location> getLocations(@PathParam("deviceId") String deviceId) {
		return new LocationController().getLocations(deviceId);
	}
	
}
