package game;

import edu.monash.fit2099.engine.*;

/**
 * an action for eating
 */
public class EatAction extends Action {

    private Item item;
    private Ground ground;
    private Actor actorEaten;
    private String nameOfFood;

    /**
     * Constructor for Item class object
     * @param item item that is eaten
     */
    public EatAction(Item item){
        this.item = item;
        this.nameOfFood = item.toString();
    }

    /**
     * Constructor for when Ground class object is being eaten by Protoceratops ie. Tree or Grass
     * @param ground ground that is eaten
     */
    public EatAction(Ground ground){
        this.ground = ground;
        this.nameOfFood = ground.toString();
    }

    /**
     * Constructor for when Actor class object is being eaten by Plesiosaurs or Pteanodons
     * ie. Fish
     * @param actor actorEaten that is eaten
     */
    public EatAction(Actor actor){
        this.actorEaten = actor;
        this.nameOfFood = actor.toString();
    }


    /**
     * Execute this action (eating) by removing the Item or replacing the Ground with Dirt
     * and increasing the foodLevel of the actorEaten performing the action
     * @param actor The actorEaten performing the action.
     * @param map The map the actorEaten is on.
     * @return the description of this action
     */
    public String execute(Actor actor, GameMap map){

        Location here = map.locationOf(actor);

        //for Tree or Grass
        if (ground != null){
            here.setGround(new Dirt());
            actor.increaseFoodLevel(ground.getFoodPoints());
        }

        //for edible Item (egg, food, corpse)
        if (item != null){
            here.removeItem(item);
            actor.increaseFoodLevel(item.getFoodPoints());
        }

        //for Fish
        if (actorEaten != null){
           // Location locationOfPrey = map.locationOf(actorEaten);
            map.removeActor(actorEaten);
            actor.increaseFoodLevel(actorEaten.getFoodPoints());

        }


        return menuDescription(actor);
    }

    /**
     * Display description of action
     * @param actor The actorEaten performing the action.
     * @return string indicating this action
     */
    public String menuDescription(Actor actor){
        return actor + " ate " + nameOfFood;
    }
}
