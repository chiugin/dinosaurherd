package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

import java.io.DataInput;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	/**
	 * Constructor
	 */
	public Floor() {
		super('_');
	}

	/**
	 * dinosaur cannot enter shop
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor instanceof Player;
	}

	/**
	 * does nothing, has to be implemented due to declaration in ActorInterface
	 * floor carries no food point, therefore it cannot be eaten
	 * @return
	 */
	public int getFoodPoints(){return 0;}

}
