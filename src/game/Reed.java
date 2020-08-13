package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * reed class that have chance to generate fish object
 */
public class Reed extends Ground {

    private final static double GENERATE_FISH_CHANCE = 0.1;

    public Reed() { super('w');}

    /**
     * serves as a ticking clock
     * @param location the location to grow reed on
     */
    @Override
    public void tick (Location location) {
        if (Overcrowded(location)){
            Water water = new Water();
            location.setGround(water);
        } else{
            double chance = Math.random();
            if (chance <= GENERATE_FISH_CHANCE){
                if (!location.containsAnActor()){
                    location.addActor(new Fish("Fish",'<',10));
                }
            }

        }


    }

    /**
     * check if the surrounding of a reed is overcrowded
     * @param location the location of a reed
     * @return true if overcrowded, otherwise false
     */
    private boolean Overcrowded(Location location){
        int reedCount = 0;
        for (Exit exit: location.getExits()){
            if (exit.getDestination().getDisplayChar() == 'w'){
                reedCount++;
            }
        }
        if (reedCount > 6){
            return true;
        }
        else{
            return false;
        }
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
