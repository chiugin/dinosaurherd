package game;

import edu.monash.fit2099.engine.Item;


/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableDinoItem extends Item {


	protected int buyValue; //the value of item if bought
	protected int sellValue; //the value of item if sold
	protected boolean edible; //if the item can be eaten by dinosaurs
	protected DinoType dinoType; //dinosaur type
	protected int foodPoints; //food points of the item


	/**
	 * Constructor for item that are edible
	 * @param name name of item
	 * @param displayChar display character on map
	 * @param buyValue buying value of item
	 * @param sellValue selling value of item
	 * @param edible true if edible, otherwise false
	 * @param dinoType dinosaur type of item
	 * @param foodPoints food point carry by the item
	 */
	public PortableDinoItem(String name, char displayChar, int buyValue, int sellValue, boolean edible, DinoType dinoType, int foodPoints) {
		super(name, displayChar, true);
		this.buyValue = buyValue;
		this.sellValue = sellValue;
		this.edible = edible;
		this.dinoType = dinoType;
		this.foodPoints = foodPoints;

	}

	/**
	 * constructor for items that are not edible like dinosaur tag
	 * @param name name of item
	 * @param displayChar display character on map
	 * @param buyValue buying value of item
	 * @param sellValue selling value of item
	 * @param edible true if edible, otherwise false
	 */
	public PortableDinoItem(String name, char displayChar, int buyValue, int sellValue, boolean edible){
		super(name, displayChar, true);
		this.buyValue = buyValue;
		this.sellValue = sellValue;
		this.edible = edible;
	}

	/**
	 * get food point carried by the item
	 * @return food point of item
	 */
	public int getFoodPoints(){return foodPoints;}

	/**
	 * get buying value of item
	 * @return buying value of item
	 */
	public int getBuyValue(){
		return buyValue;
	}

	/**
	 * get selling value of item
	 * @return selling value of item
	 */
	public int getSellValue(){
		return sellValue;
	}


}
