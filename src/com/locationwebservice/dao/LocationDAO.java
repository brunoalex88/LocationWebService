package com.locationwebservice.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
	
	public int insertLocation(Location location) {
		Connection conn        = null;
		PreparedStatement stmt = null;
		String sql             = "INSERT INTO LOCATION VALUES (?, ?, ?, ?)";
		
		conn = getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, location.getDeviceId());
			stmt.setDouble(2, location.getLatitude());
			stmt.setDouble(3, location.getLongitude());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
			stmt.setDate  (4, new Date(sdf.parse(location.getTime()).getTime()));
			stmt.executeQuery();
			return 0;
		} catch (Exception e) {
			System.out.println("Erro ao executar o insert no Oracle:");
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public ArrayList<Location> getLocations(String deviceId) {
		Connection conn 	   		  = null;
		PreparedStatement stmt 		  = null;
		ResultSet rs 		   		  = null;
		ArrayList<Location> locations = null;
		String sql  		   		  = "SELECT LATITUDE, LONGITUDE, TO_CHAR(TIME, 'DD/MM/YYYY HH24:MI:SS') TIME FROM LOCATION WHERE DEVICE_ID = ?";

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
				l.setTime(rs.getString("TIME"));
				
				locations.add(l);
				
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao buscar as localizações:");
			e.printStackTrace();
		}
		
		return locations;
		
	}
	
}
