package edu.monash.fit2099.demo.conwayslife;

import edu.monash.fit2099.engine.Ground;

public class Floor extends Ground {

	public Floor() {
		super('.');
		addSkill(Status.DEAD);
	}


	public int getFoodPoints(){return 0;}
}
