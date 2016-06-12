package com.locationwebservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.locationwebservice.factory.ConnectionFactory;
import com.locationwebservice.model.Location;

public class LocationDAO extends ConnectionFactory {
	private static LocationDAO instance;
	
	public static LocationDAO getInstance() {
		
		if (instance == null)
			instance = new LocationDAO();
		
		return instance;
		
	}
	
	public ArrayList<Location> getLocations(String deviceId) {
		Connection conn 	   		  = null;
		PreparedStatement stmt 		  = null;
		ResultSet rs 		   		  = null;
		ArrayList<Location> locations = null;
		String sql  		   		  = "SELECT LATITUDE, LONGITUDE, TIME FROM LOCATION WHERE DEVICE_ID = ?";

		conn 	  = getConnection();
		locations = new ArrayList<Location>();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, deviceId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Location l = new Location();
				l.setDeviceId(deviceId);
				l.setLatitude(rs.getDouble("LATITUDE"));
				l.setLongitude(rs.getDouble("LONGITUDE"));
				l.setTime(rs.getDate("TIME"));
				
				locations.add(l);
				
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao buscar as localizações:");
			e.printStackTrace();
		}
		
		return locations;
		
	}
	
}
