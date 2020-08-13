package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * tree on map
 */
public class Tree extends Ground {

	private final static double GROW_CHANCE = 0.005;
	private int age = 0;
	private String name;
	private int foodPoints;

	/**
	 * constructor
	 */
	public Tree() {
		super('+');
		this.name = "tree";
		this.foodPoints = 10;
	}

	/**
	 * tick of tree, allowing growth of existing trees and potential growth of tree around existing ones
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';


		for (Exit exit : location.getExits()) {
			Location here = exit.getDestination();
			if (!(here.getGround() instanceof Tree) && here.getGround() instanceof Dirt) {
				double chance = Math.random();
				if (chance <= GROW_CHANCE) {
					Tree tree = new Tree();
					here.setGround(tree);
				}
			}
		}
	}


	/**
	 * return false, if water based creatures
	 * @param actor the Actor to check
	 * @return false if water-based, true if land-based
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor instanceof Plesiosaurs || actor instanceof Fish ) {
			return false;
		}
		else {return true;}
	}

	/**
	 * name of Tree object
	 * @return name
	 */
	@Override
	public String toString(){
	    return name;
    }



	/**
	 * get the food point it carries
	 * @return food point
	 */
	public int getFoodPoints(){
		return foodPoints;
	}

}
