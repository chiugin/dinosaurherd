package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	private final static double GROW_CHANCE = 0.005 ;

	/**
	 * constructor
	 */
	public Dirt() {
		super('.');
	}

	/**
	 * Has a chance to grow grass on the piece of dirt on each turn
	 * @param location the location to grow grass on
	 */
	@Override
	public void tick (Location location) {
		double chance = Math.random();
		if (chance <= GROW_CHANCE) {
			Grass grass = new Grass();
			location.setGround(grass); // update the Ground type on this location if grass is grown
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
	 * does nothing, has to be implemented due to declaration in ActorInterface
	 * dirt does not carry any food point, therefore actor does not consume it
	 * @return 0
	 */
	public int getFoodPoints(){return 0;}

}
