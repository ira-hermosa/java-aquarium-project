package com.qa.aQAriamProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AquariumManager {
	
	//Create a DBManager object for use in the code
	DBManager manager = new DBManager();

	//Making a connection object and have the manager object call the ConnectDB from the DBManager class
	Connection conn = manager.connectDB();

	PreparedStatement preState;

	
	private ArrayList<Fish> orderBasket = new ArrayList<Fish>();
	
	
	public Statement databaseSetup() {
		Statement statement = null;
		try {statement = conn.createStatement();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return statement;
		
	}
	
	
	
	public boolean addFish(Fish fish) {
		try {
			String query = "INSERT INTO fish (type, colour, length, cost) VALUES (?,?,?,?);";
			preState = conn.prepareStatement(query);

			preState.setString(1, fish.getType());
			preState.setString(2, fish.getColour());
			preState.setInt(3, fish.getLength());
			preState.setFloat(4, fish.getCost());
			preState.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Fish getFishId(int id) {
		try {

			String query = "SELECT * FROM fish WHERE id = ?";
			preState = conn.prepareStatement(query);
			preState.setInt(1, id);

			ResultSet result = preState.executeQuery();
			// Without using result.next() the first line of result are the headers "type", "colour", "length", "cost"
			result.next();
			return manager.convertResults(result);
		

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Fish> getAllFish() {
	
		ArrayList<Fish> fishList = new ArrayList<Fish>();
		try {
			String query = "SELECT * FROM fish";
			preState = conn.prepareStatement(query);
			ResultSet result = preState.executeQuery(query);
			
			//while there is another row of data below the current, move down the row and use the new data
			while (result.next()) {
				Fish newFish = manager.convertResults(result);
				fishList.add(newFish);
			}
			return fishList;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	public boolean updateFishById(int id, Fish fish) {
	
		try {String query = "UPDATE fish SET type = ?, colour = ?, length = ?, cost = ? WHERE id = ?";

		preState = conn.prepareStatement(query);
		preState.setString(1, fish.getType());
		preState.setString(2, fish.getColour());
		preState.setInt(3, fish.getLength());
		preState.setFloat(4, fish.getCost());
		preState.setFloat(5, id);
		preState.executeUpdate();
		return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	

	public boolean updateFishByQuery(Fish fish, String queryHeader, String value) {
		
		try {
			String query = "UPDATE fish SET type = ?, colour = ?, length = ?, cost = ? WHERE " + queryHeader + " = ?";
			preState = conn.prepareStatement(query);
			preState.setString(1, fish.getType());
			preState.setString(2, fish.getColour());
			preState.setInt(3, fish.getLength());
			preState.setFloat(4, fish.getCost());
			preState.setString(5, value);
			preState.executeUpdate();
			return true;	
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean deleteFishById(int id) {
	
		try {
			String query = "DELETE FROM fish WHERE id = ?";
			preState = conn.prepareStatement(query);
			preState.setInt(1, id);
			preState.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public boolean deleteAllFish() {
		try {
			String query = "DELETE FROM fish where id>=0";
			preState = conn.prepareStatement(query);
			preState.executeUpdate();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	

//	Method takes in an ID, finds a fish with that ID and adds it to the orderBasket ArrayList
	public ArrayList<Fish> addFishToOrderBasket(int id) {
		
		try {
			String query = "SELECT * FROM fish where id = ?";
			preState = conn.prepareStatement(query);
			preState.setInt(1, id);
			ResultSet result = databaseSetup().executeQuery(query);
			
			while (result.next()) {
				Fish newFish = manager.convertResults(result);
				orderBasket.add(newFish);
			}
			return orderBasket;
										
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

//	Method returns the total cost of all fish in the basket
	public float buyFish() {
		try {
			
			float totalSum=0f;
			for (Fish fish: orderBasket) {
				totalSum += fish.getCost();
			}
			return totalSum;	
			
		} catch (Exception e) {
			e.printStackTrace();
			return (Float) null;
		}
		
		
	}

}
