package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * water type ground
 */
public class Water extends Ground {

    private final static double NEXT_TO_LAND_GROW_CHANCE = 0.1;
    private final static double NEXT_TO_REED_GROW_CHANCE = 0.05 ;

    /***
     * Constructor
     */
    public Water (){ super('~'); }



    /**
     * serves as a ticking clock
     * @param location the location to grow reed on
     */
    @Override
    public void tick (Location location) {

        double chance = Math.random();
        if (isNextToLand(location)){
            if (chance <= NEXT_TO_LAND_GROW_CHANCE){
                Reed reed = new Reed();
                location.setGround(reed);
            }
        } else if (isNextToReed(location)){
            if (chance <= NEXT_TO_REED_GROW_CHANCE){
                Reed reed = new Reed();
                location.setGround(reed);
            }
        }

    }


    /**
     * check if a water type ground is next to a land type ground
     * @param location the location of water type ground
     * @return true if it is next to a land type ground, otherwise false
     */
    private boolean isNextToLand(Location location){
        for (Exit exit: location.getExits()){
            if (exit.getDestination().getDisplayChar() == '.'){
                return true;
            }
        }
        return false;
    }

    /**
     * check if there is reed object next to this water type ground
     * @param location the location of the water type ground
     * @return if there is a reed then true, otherwise false
     */
    private boolean isNextToReed(Location location){
        for (Exit exit: location.getExits()){
            if (exit.getDestination().getDisplayChar() == 'w'){
                return true;
            }
        }
        return false;
    }




    /**
     * return false, as player and land dinosaurs cannot go to water
     * @param actor the Actor to check
     * @return true if water-based or flying dino, false if land-based
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof Plesiosaurs || actor instanceof Fish || actor instanceof Pteanodons) {
            return true;
        }
        else {return false;}
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
