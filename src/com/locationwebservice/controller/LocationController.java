package com.locationwebservice.controller;

import java.util.ArrayList;

import com.locationwebservice.dao.LocationDAO;
import com.locationwebservice.model.Location;

public class LocationController {

	public ArrayList<Location> getLocations(String deviceId) {
		return LocationDAO.getInstance().getLocations(deviceId);
	}
	
	public int insertLocation(Location location) {
		return LocationDAO.getInstance().insertLocation(location);
	}
	
}
