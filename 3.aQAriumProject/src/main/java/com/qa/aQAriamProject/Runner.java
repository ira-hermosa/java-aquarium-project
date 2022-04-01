package com.qa.aQAriamProject;

public class Runner {

	public static void main(String[] args) {
		
		DBManager db = new DBManager();
		AquariumManager manager = new AquariumManager();
		
		System.out.println(manager.getAllFish());
		manager.addFish(new Fish("green fin", "green", 5, 7));
		System.out.println(manager.getFishId(2));
		System.out.println(manager.getAllFish());
		manager.updateFishById(1, new Fish("sardine", "grey", 4, 3)); 
		manager.updateFishByQuery(new Fish("striped fin", "black", 4, 5), "colour", "white" ); 
		manager.deleteFishById(4);
		manager.deleteAllFish();
		manager.addFishToOrderBasket(5); 
		System.out.println(manager.buyFish());

	}

}
