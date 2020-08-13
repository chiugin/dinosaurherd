package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * wall around shop
 */
public class Wall extends Ground {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * return false, as actor cannot go through walls
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * return true, as object cannot be place on wall
	 * @return true (object is blocked)
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * does nothing, has to be implemented due to declaration in ActorInterface
	 * does not carry any food point, as it cannot be eaten
	 * @return
	 */
	public int getFoodPoints(){return 0;}
}
