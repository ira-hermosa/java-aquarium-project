package com.qa.aQAriamProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AquariumManager {
	
	DBManager manager = new DBManager();

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
			result.next();
			return manager.convertResults(result);
		

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Fish> getAllFish() {
		// TODO - Returns an arrayList of all fish from DB
		ArrayList<Fish> fishList = new ArrayList<Fish>();
		try {
			String query = "SELECT * FROM fish";
			ResultSet result = databaseSetup().executeQuery(query);
			
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
		// TODO - Updates a fish by ID
		
		try {String query = "UPDATE fish SET type = ?, colour = ?, length = ?, cost = ? WHERE id = ?";
		PreparedStatement preStmt = conn.prepareStatement(query);
		
		preStmt.setString(1, fish.getType());
		preStmt.setString(2, fish.getColour());
		preStmt.setInt(3, fish.getLength());
		preStmt.setFloat(4, fish.getCost());
		preStmt.setFloat(4, id);
		
		
		preStmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateFishByQuery(Fish fish, String queryHeader, String value) {
		// TODO - Updates all fish that match criteria
		try {
			String query = "UPDATE fish SET type = ?, colour = ?, length = ?, cost = ? WHERE " + queryHeader + " = ?";
			PreparedStatement preStmt = conn.prepareStatement(query);
			
			preStmt.setString(1, fish.getType());
			preStmt.setString(2, fish.getColour());
			preStmt.setInt(3, fish.getLength());
			preStmt.setFloat(4, fish.getCost());
			preStmt.setString(5, value);
			preStmt.executeUpdate();
			return true;	
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	
	public boolean deleteFishById(int id) {
		// TODO - Deletes a fish by ID
		
		try {
			String query = "DELETE FROM fish WHERE id = ?";
			PreparedStatement preState = conn.prepareStatement(query);
			preState.setInt(1, id);
			preState.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public boolean deleteAllFish() {
		// TODO - Deletes all fish
		try {
			String query = "DELETE * FROM fish ";
			PreparedStatement preState = conn.prepareStatement(query);
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<Fish> addFishToOrderBasket(int id) {
		// TODO - Takes in an ID, finds a fish with that ID and adds it to the
		// orderBasket ArrayList
		
		try {
			String query = "SELECT * FROM fish WHERE id = ?";
			
			ResultSet result = databaseSetup().executeQuery(query);
			result.next();
				Fish newFish = manager.convertResults(result);
				orderBasket.add(newFish);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return orderBasket;
		
	}

	public float buyFish() {
		// TODO - Returns the total cost of all fish in the basket and clears the basket
		// STRETCH - Removes the bought fish from the aquarium
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
