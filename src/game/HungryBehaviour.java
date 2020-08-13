package game;

import edu.monash.fit2099.engine.*;

/**
 * hungry behaviour of an actor, so that it will look for nearest food to consume
 */
public class HungryBehaviour implements Behaviour {

    /**
     * Get the action that actor is able to perform
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return move action towards the destination if the food is not already at the same location as the actor
     */
    @Override
    public Action getAction (Actor actor, GameMap map){

        //if current location has food, then dinosaur will return EatAction();
        Location here = map.locationOf(actor);
        if(gotFood(actor,here)){
            if (actor instanceof Protoceratops){
                for (Item item : here.getItems()){
                    if (item.getDisplayChar() == 'h'){
                        return new EatAction(item);
                    }
                }
                if (here.getGround() instanceof Tree || here.getGround() instanceof Grass){
                    return new EatAction(here.getGround());
                }
            }
            if (actor instanceof Velociraptors || actor instanceof Trex){
                for (Item item: here.getItems()){
                    if (item.getDisplayChar() == 'c' || item.getDisplayChar() == 'o' || item.getDisplayChar() == '%'){
                        return new EatAction(item);
                    }
                }
            }
            if (actor instanceof Pteanodons || actor instanceof Plesiosaurs) {
                for (Item item : here.getItems()) {
                    if (item.getDisplayChar() == '%' || item.getDisplayChar() == 'm' || item.getDisplayChar() == 'c' ) {
                        return new EatAction(item);
                    }
                }
                for (Exit exit: here.getExits()){
                    if (exit.getDestination().containsAnActor() && exit.getDestination().getActor() instanceof Fish){
                        return new EatAction(exit.getDestination().getActor());
                    }
                }
            }

        }
        else {

            // if surrounding locations of carnivorous dinosaur has Protoceratops, then perform AttackAction()
            if (actor instanceof Velociraptors || actor instanceof Pteanodons || actor instanceof Trex) {
                for (Exit exit : here.getExits()) {
                    Location destination = exit.getDestination();
                    if (destination.containsAnActor() && (destination.getActor() instanceof Protoceratops || destination.getActor() instanceof Player)) {
                        return new AttackAction(destination.getActor());
                    }
                }
            }

            Location there = findNearestFoodLocation(actor, map);
            //if found food, will move towards it
            if (there != null) {
                System.out.println("Food found at (" + there.x() + "," + there.y() + ")");
                int currentDistance = distance(here, there);
                System.out.println("Distance to food is " + currentDistance + " steps.");
                if (actor instanceof Pteanodons && currentDistance > 1) {
                    // Pteanodons moves 2 steps to get to food when current distance is more than or equal to 2
                    int x = map.locationOf(actor).x();
                    int y = map.locationOf(actor).y();
                    for (int i = -2; i <= 2; i += 4) {
                        for (int j = 2; j >= -2; j--) {
                            if(validLocation(x+i,y+j,map)){
                                Location spotA = map.at(x + i, y + j);
                                int newDistanceA = distance(spotA, there);
                                if (newDistanceA < currentDistance && spotA.canActorEnter(actor)) {
                                    return new MoveActorAction(spotA, "to food");
                                }
                            }
                            if(validLocation(x+j,y+i,map)){
                                Location spotB = map.at(x + j, y + i);
                                int newDistanceB = distance(spotB, there);
                                if (newDistanceB < currentDistance && spotB.canActorEnter(actor)) {
                                    return new MoveActorAction(spotB, "to food");
                                }
                            }
                        }
                    }
                }
                else { // when its other dinosaur or current distance of food from Pteanodons to food is 1
                    for (Exit exit : here.getExits()) {
                        Location destination = exit.getDestination();
                        if (destination.canActorEnter(actor)) {
                            int newDistance = distance(destination, there);
                            if (newDistance < currentDistance ) {
                                return new MoveActorAction(destination, exit.getName());
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * check if that location has food (Tree, Grass or herbivore food) if actor is Protoceratops
     * or location has food (corpse, egg or carnivore food) or Protoceraptops if actor is Velociraptors
     * @param location to be checked
     * @return true if food is present otherwise false
     */
    private boolean gotFood(Actor actor,Location location){
        if (actor instanceof Protoceratops){
            for (Item item: location.getItems()){
                if (item.getDisplayChar() == 'h'){
                    return true;
                }
            }
            if (location.getGround() instanceof Tree){
                return true;
            }
            if (location.getGround() instanceof Grass){
                return true;
            }
        }

        if (actor instanceof Pteanodons || actor instanceof Plesiosaurs){
            for (Item item : location.getItems()) {
                if ( item.getDisplayChar() == '%' || item.getDisplayChar() == 'm' ) {
                    return true;
                }
            }
            for (Exit exit: location.getExits()){
                if (exit.getDestination().getActor() instanceof Fish){
                    return true;
                }
            }
        }


        if (actor instanceof Velociraptors || actor instanceof Trex){
            for (Item item: location.getItems()){
                if (item.getDisplayChar() == 'c' || item.getDisplayChar() == 'o' || item.getDisplayChar() == '%'){
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * find the nearest location of food to actor
     * @param actor Actor looking for food
     * @param map GameMap containing actor
     * @return the location of the nearest food
     */
    private Location findNearestFoodLocation(Actor actor, GameMap map){

        // x,y coordinate of actor
        int x = map.locationOf(actor).x();
        int y = map.locationOf(actor).y();
        // find the maximum steps the actor can take until it reaches the border of the GameMap
        int max_1 = Math.max((map.getYRange().max()) - y, y);
        int max_2 = Math.max(x, (map.getXRange().max()) - x);
        int maxDistance = Math.max(max_1, max_2);
        //d is the index for each spiral loop around actor
        int d = 1;
        // a loop then go in a spiral way around the actor to look for the nearest food
        while(d < maxDistance) { //!success ||
            for (int i = 0; i < d + 1; i++) {
                // check if its a valid
                if (validLocation(x + i, y + d, map)) {
                    Location loc1 = map.at(x + i, y + d);
                    if (gotFood(actor,loc1)) {
                        return loc1;
                    }
                }
                if (validLocation(x + d, y - i, map)) {
                    Location loc2 = map.at(x + d, y - i);
                    if (gotFood(actor,loc2)) {
                        return loc2;
                    }
                }
                if (validLocation(x - i, y - d, map)) {
                    Location loc3 = map.at(x - i, y - d);
                    if (gotFood(actor,loc3)) {
                        return loc3;
                    }
                }
                if (validLocation(x - d, y + i, map)) {
                    Location loc4 = map.at(x - d, y + i);
                    if (gotFood(actor,loc4)) {
                        return loc4;
                    }
                }
            }
            for (int j = 1; j < d; j++) {
                if(validLocation(x+j,y-d,map)) {
                    Location loc5 = map.at(x + j, y - d);
                    if (gotFood(actor,loc5)) {
                        return loc5;
                    }
                }
                if(validLocation(x-d,y-j,map)) {
                    Location loc6 = map.at(x - d, y - j);
                    if (gotFood(actor,loc6)) {
                        return loc6;
                    }
                }
                if(validLocation(x+d,y+j,map)) {
                    Location loc7 = map.at(x + d, y + j);
                    if (gotFood(actor,loc7)) {
                        return loc7;
                    }
                }
                if(validLocation(x-j,y+d,map)) {
                    Location loc8 = map.at(x - j, y + d);
                    if (gotFood(actor,loc8)) {
                        return loc8;
                    }
                }
            }
            d++;
        }

        //return null if no location that contains food found in the whole GameMap
        return null;
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
    /**
     * using diagonal distance to calculate steps needed for eight direction(1 step for diagonal)
     * got from url:http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
     * @param a location a
     * @param b location b
     * @return the number of steps between a and b if you move in the eight cardinal directions.
     */
    private int distance(Location a, Location b) {
        int dx = Math.abs(a.x() - b.x());
        int dy = Math.abs(a.y() - b.y());
        return (dx + dy) + (-1) * Math.min(dx, dy);
    }
}
