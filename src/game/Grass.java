package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
/**
 * Grass on the ground
 */
public class Grass extends Ground {

    private String name;
    private int foodPoints;

    /**
     * Constructor
     */
    public Grass() {
        super('"');
        this.name = "grass";
        this.foodPoints = 5;
    }

    /**
     * return name of object of this class
     * @return
     */
    @Override
    public String toString(){
        return name;
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
     * food point that an actor is able to get by consuming this
     * @return food point carried by Grass object
     */
    public int getFoodPoints(){
        return foodPoints;
    }
}
