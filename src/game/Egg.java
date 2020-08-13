package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * Egg class which extends PortableDinoItem
 */
public class Egg extends PortableDinoItem{

    private int age;
    private Random rand = new Random();
    private boolean captiveBreed;

    /**
     * Constructor
     * @param name name of egg
     * @param displayChar display character for egg
     * @param buyValue buying value of egg
     * @param sellValue selling value of egg
     * @param edible true if can be consume by other actor
     * @param dinoType type of dinosaur (Protoceratop or Velociraptor)
     * @param foodPoints food point that is carried by egg
     * @param age age of egg
     */
    public Egg(String name, char displayChar, int buyValue, int sellValue, boolean edible, DinoType dinoType, int foodPoints, int age, boolean captiveBreed){
        super(name, displayChar, buyValue, sellValue, edible, dinoType, foodPoints);
        this.age = age;
        this.captiveBreed = captiveBreed;
    }


    /**
     * Tick for egg on the ground, adds on age for each turn
     * @param location location of egg on Ground
     */
    @Override
    public void tick (Location location){
        age++;
        if (age >= 20){

            if (dinoType == DinoType.PLESIO){
                if (isNextToWater(location)){
                    Location destination = getLocationOfWater(location);
                    if (destination != null && !destination.containsAnActor()){
                        location.removeItem(this);
                        hatch(destination);
                    }

                }
            }
            else{
                //check if there is actor on the location! If there is, it won't hatch
                //and have to wait till next turn to see if it can hatch
                if (!location.containsAnActor()){
                    location.removeItem(this);
                    hatch(location);
                }
            }

        }
    }


    /**
     * Tick for egg in the inventory of actor, adds on age for each turn
     * @param location location of actor
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location location, Actor actor){
        age++;
        if (age>= 20){
            if (dinoType == DinoType.PLESIO){
                if (isNextToWater(location)){
                    Location destination = getLocationOfWater(location);
                    if (destination != null && !destination.containsAnActor()){
                        location.removeItem(this);
                        hatch(destination);
                    }

                }
            }
            else{
                //add new baby dinosaur at space (an exit) that is not water next to player that is randomly chosen
                //if no space around the player, then wait next tick (turn) until an empty space is found
                ArrayList<Exit> exits = new ArrayList<Exit>();

                for (Exit exit : location.getExits()){
                    Location destination = exit.getDestination();
                    if (!destination.containsAnActor() && destination.getDisplayChar() != 'w' && destination.getDisplayChar() != '~'){
                        exits.add(exit);
                    }
                }

                if (!exits.isEmpty()){
                    actor.removeItemFromInventory(this);

                    Exit exit = exits.get(rand.nextInt(exits.size()));
                    Location here = exit.getDestination();
                    hatch(here);

                }

            }
        }

    }

    /**
     * checking if an a location is next to water type ground
     * @param location the location to check
     * @return true if is water, otherwise false
     */
    private boolean isNextToWater(Location location){
        for (Exit exit: location.getExits()){
            if (exit.getDestination().getDisplayChar() == '~' || exit.getDestination().getDisplayChar() == 'w'){
                return true;
            }
        }
        return false;

    }

    /**
     * return the location if surrounding grounds are water type
     * @param location the location to check (as source)
     * @return the found location otherwise null
     */
    private Location getLocationOfWater(Location location){
        for (Exit exit: location.getExits()){
            if (exit.getDestination().getDisplayChar() == '~' || exit.getDestination().getDisplayChar() == 'w'){
                return exit.getDestination();
            }
        }
        return null;

    }


    /**
     * adds a new dinosaur on location on land if it hatched
     * @param location current location of egg
     */
    private void hatch(Location location){
        if (dinoType == DinoType.PROTO){
            System.out.println("Protoceratops egg has hatched at ("+location.x()+","+location.y()+")");
            location.addActor(new Protoceratops("Protoceratops", 0, 10, captiveBreed));
        }
        if (dinoType == DinoType.VELO){
            System.out.println("Velociraptors egg has hatched at ("+location.x()+","+location.y()+")");
            location.addActor(new Velociraptors("Velociraptors", 0, 40, captiveBreed));
        }

        if (dinoType == DinoType.PLESIO){
            System.out.println("Plesiosaurs egg has hatched at ("+location.x()+","+location.y()+")");
            location.addActor(new Plesiosaurs("Plesiosaurs", 0, 40, captiveBreed));
        }
        if (dinoType == DinoType.PTEANO) {
            System.out.println("Pteanodons egg has hatched at (" + location.x() + "," + location.y() + ")");
            location.addActor(new Pteanodons("Pteanodons", 0, 40, captiveBreed));
        }
        if (dinoType == DinoType.TREX){
            System.out.println("T-rex egg has hatched at ("+location.x()+","+location.y()+")");
            location.addActor(new Trex("Trex", 0, 40, captiveBreed));
        }


    }




}
