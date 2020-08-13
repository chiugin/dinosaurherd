package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.WeaponItem;

public class Stick extends WeaponItem {

	public Stick() {
		super("stick", '/', 10, "pokes");
	}


	public int getFoodPoints(){return 0;}

	public int getBuyValue(){return 0;}

	public int getSellValue(){
		return 0;
	}
}

