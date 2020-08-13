package game;

import edu.monash.fit2099.engine.*;

/**
 * class for the edge of the map to enable the player to travel through different maps
 */
public class EdgeMap extends Ground {

    /**
     * Constructor
     */
    public EdgeMap() {super('^');}


    /**
     * actor cannot be on the same position as the edgemap
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * does nothing, has to be implemented due to declaration in ActorInterface
     * get the food point it carries
     * @return food point
     */
    public int getFoodPoints(){
        return 0;
    }

}
