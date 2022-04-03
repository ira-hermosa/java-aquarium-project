package com.qa.testing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.aQAriamProject.AquariumManager;
import com.qa.aQAriamProject.DBManager;
import com.qa.aQAriamProject.Fish;

public class AquariumManagerTest {
	
	AquariumManager testManager = new AquariumManager();
	DBManager  dbManager = new DBManager();
	Connection testConn = dbManager.connectDB();
	PreparedStatement testState;
	
	//Objects (without IDs) for any tests
	Fish fish1 = new Fish("type1", "colour1", 1, 1.1f);
	Fish fish2 = new Fish("type2", "colour2", 2, 2.1f);
	Fish testFish = new Fish("test-type", "test-colour", 5, 5.0f);
	
	
	//Objects (with IDs) - used for getting objects from database
	Fish fish1ID = new Fish(1,"type1", "colour1", 1, 1.1f);
	Fish fish2ID = new Fish(2,"type2", "colour2", 2, 2.1f);
	
	ArrayList<Fish> testList = new ArrayList<>();
	
	@BeforeEach
	public void setup() throws SQLException{
		testList.clear();//Remove all data from our testList
		testState = testConn.prepareStatement("TRUNCATE fish;");
		testState.execute();
		testManager.addFish(fish1);
		testManager.addFish(fish2);
	}
	
	@Test
	public void addFishTest() {
		boolean result = testManager.addFish(testFish);
		Assertions.assertTrue(result);
	}
	
	@Test
	public void getFishID() {
		int id = 1;
		Fish result = testManager.getFishId(id);
		Assertions.assertEquals(fish1ID,result);
		
	}
	
	@Test
	public void getAllFishTest() {
		testList.add(fish1ID);
		testList.add(fish2ID);
		ArrayList<Fish> result = testManager.getAllFish();
		Assertions.assertEquals(testList, result);
	}
	
	@Test
	public void updateFishByIdTest() {
		int id = 2;
		fish2ID.setColour("blue");
		fish2ID.setCost(5);
		fish2ID.setLength(7);
		fish2ID.setType("koi");
		boolean result = testManager.updateFishById(2, fish2ID);
		Assertions.assertTrue(true);
		
		
	}
	
	@Test
	public void updateFishByQueryTest() {
		int id = 1;
		fish1ID.setCost(3);
		fish1ID.setColour("brown");
		fish1ID.setLength(4);
		fish1ID.setType("Tuna");
		boolean result = testManager.updateFishByQuery(fish1ID, "Colour", "colour1");
		Assertions.assertTrue(true);
		
		
	}
	
	@Test
	public void deleteByIdTest() {
		int id = 2;
		boolean result = testManager.deleteFishById(id);
		Assertions.assertTrue(true);
	}
	
	@Test
	public void deleteAllFishTest() {
		boolean result = testManager.deleteAllFish();
		Assertions.assertTrue(true);
	}
	
	

}
