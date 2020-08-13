package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * an action for breeding
 */
public class BreedAction extends Action {


    /**
     * Constructor
     */
    public BreedAction(){}

    /**
     * Execute breeding
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the description of actor for laying egg
     */
    public String execute(Actor actor, GameMap map){
        if (actor instanceof Protoceratops){
            map.locationOf(actor).addItem(new Egg(actor + " egg", 'o', 50,10 , true, DinoType.PROTO, 10, 0, true));
            System.out.println("A Protoceratops egg is laid at ("+map.locationOf(actor).x()+","+map.locationOf(actor).y()+")");
        }
        if (actor instanceof Velociraptors){
            map.locationOf(actor).addItem(new Egg(actor + " egg", 'o', 1000,200 , true, DinoType.VELO, 10, 0 , true));
            System.out.println("A Velociraptors egg is laid at ("+map.locationOf(actor).x()+","+map.locationOf(actor).y()+")");
        }

        if (actor instanceof Plesiosaurs){
            map.locationOf(actor).addItem(new Egg(actor + "egg", 'o',6000,1200, true , DinoType.PLESIO, 10, 0, true));
            System.out.println("A Plesiosaurs egg is laid at (" + map.locationOf(actor).x() + "," + map.locationOf(actor).y() + ")");
        }

        if (actor instanceof Pteanodons){
            map.locationOf(actor).addItem(new Egg(actor + "egg", 'o',6000,1200, true , DinoType.PTEANO, 10, 0, true));
            System.out.println("A Pteanodons egg is laid at (" + map.locationOf(actor).x() + "," + map.locationOf(actor).y() + ")");
        }

        if (actor instanceof Trex){
            map.locationOf(actor).addItem(new Egg(actor + "egg", 'o',60000,12000, true , DinoType.TREX, 10, 0, true));
            System.out.println("A Trex egg is laid at (" + map.locationOf(actor).x() + "," + map.locationOf(actor).y() + ")");
        }

        return menuDescription(actor);
    }

    /**
     * Description of this class (breeding)
     * @param actor The actor performing the action.
     * @return description indicating an egg is laid by the actor.
     */
    public String menuDescription(Actor actor){
        return actor + " laid an egg ";
    }

}
