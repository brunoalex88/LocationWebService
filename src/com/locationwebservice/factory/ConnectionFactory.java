package com.locationwebservice.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {
	private static final String DRIVER   = "oracle.jdbc.OracleDriver";
	private static final String URL      = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static final String USER     = "followstep";
	private static final String PASSWORD = "aabb123456";

	public Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.out.println("Erro ao criar a conexão com o Oracle: ");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if (conn != null)
				conn.close();
			
			if (pstmt != null) 
				pstmt.close();
			
			if (rs != null)
				rs.close();
			
		} catch (Exception e) {
			System.out.println("Erro ao fechar a conexão com o Oracle:");
			e.printStackTrace();
		}
		
	}
	
}
