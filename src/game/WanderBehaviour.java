package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * an actor wandering on the map
 */
public class WanderBehaviour implements Behaviour {
	
	private Random random = new Random();


	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();

		// only Pteanodons can move up to 2 steps
		if(actor instanceof Pteanodons) {
			int x = map.locationOf(actor).x();
			int y = map.locationOf(actor).y();
			for(int i = -2; i <= 2; i++) {
				for (int j = -2; j <= 2; j++){
					if(!(i == 0 && j ==0)) { // it does not wander on its own position
					    if(validLocation(x+j,y+i,map)) {
                            Location spot = map.at(x + j, y + i);
                            if (spot.canActorEnter(actor)) {
                                actions.add(spot.getMoveAction(actor, "around", "-"));
                            }
                        }
					}
				}
			}
		}else{
			for (Exit exit : map.locationOf(actor).getExits()) {
				Location destination = exit.getDestination();
				if (destination.canActorEnter(actor)) {
					actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
				}
			}
		}
		// choose a random valid place to move to
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}

    /**
     * check if coordinate x,y is within the border of the map
     * @param x x coordinate
     * @param y y coordinate
     * @param map the map
     * @return true if is within boundaries, otherwise false
     */
    private boolean validLocation(int x, int y, GameMap map){
        int max_y = map.getYRange().max();
        int max_x = map.getXRange().max();
        return x<=max_x && x>=0 && y<=max_y && y>=0;
    }

}
