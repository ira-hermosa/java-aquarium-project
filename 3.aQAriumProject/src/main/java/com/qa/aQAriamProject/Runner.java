package com.qa.aQAriamProject;

public class Runner {

	public static void main(String[] args) {
		
		DBManager db = new DBManager();
		AquariumManager manager = new AquariumManager();
		
//		manager.addFish(new Fish("green fin", "green", 5, 7));
//		manager.addFish(new Fish("tuna", "pink", 8, 7));
//		manager.addFish(new Fish("koi", "orange", 8, 9));
		
		System.out.println(manager.getAllFish());
		System.out.println(manager.getFishId(1));
		System.out.println(manager.getAllFish());
		System.out.println(manager.updateFishById(1, new Fish("sardine", "grey", 4, 3))); 
		manager.updateFishByQuery(new Fish("striped fin", "black", 4, 5), "colour", "white" ); 
//		manager.deleteFishById(3);
//		manager.deleteAllFish();
		System.out.println(manager.addFishToOrderBasket(1));
		System.out.println(manager.buyFish());

	}

}
