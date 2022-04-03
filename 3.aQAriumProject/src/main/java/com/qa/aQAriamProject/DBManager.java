package com.qa.aQAriamProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

//Connect to the database, send queries and receive data
public class DBManager {
	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/aquarium?serverTimezone=BST";
	final String USER = "root";
	final String PASSWORD = "root";

//Import an object of Connection with java.sql.Connection;
	Connection conn;
	
	
//Method to connect to database, returning a Connection object
	public Connection connectDB() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			return conn;
		}
		System.out.println("Connected to Database succesfully");
		return conn;

	}
	

//Method to convert ResultSet to fish objects
	public Fish convertResults(ResultSet result) {
		try {
			int id = result.getInt("id");
			String type = result.getString("type");
			String colour = result.getString("colour");
			int length = result.getInt("length");
			float cost = result.getFloat("cost");

			Fish fish = new Fish(id, type, colour, length, cost);
			return fish;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
