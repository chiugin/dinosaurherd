package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * an action for actor dying
 */
public class DieAction extends Action {

    /**
     * remove actor from Gamemap and add a corpse onto Gamemap to replace the actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return return the description carried out by the actor
     */
    public String execute(Actor actor, GameMap map){

        if (actor instanceof Fish){
            map.removeActor(actor);
        }

        if (actor instanceof Dinosaurs){
            int sellValue = 0;
            DinoType dinoType = DinoType.PROTO;
            if (actor instanceof Protoceratops){
                sellValue = 15;
                dinoType = DinoType.PROTO;
            }
            if (actor instanceof Velociraptors){
                sellValue = 150;
                dinoType = DinoType.VELO;
            }
            if (actor instanceof Plesiosaurs){
                sellValue = 300;
                dinoType = DinoType.PLESIO;
            }
            if (actor instanceof Pteanodons){
                sellValue = 300;
                dinoType = DinoType.PTEANO;
            }
            if (actor instanceof Trex){
                sellValue = 300;
                dinoType = DinoType.TREX;
            }
            Item corpse = new PortableDinoItem("dead " + actor, '%', 0,sellValue, true, dinoType, 50);
            map.locationOf(actor).addItem(corpse);
            map.removeActor(actor);


        }
        return menuDescription(actor);

    }

    /**
     * Description of this class (death of actor)
     * @param actor The actor performing the action.
     * @return string indicating an actor is dead.
     */
    public String menuDescription(Actor actor) {
        return actor + " is dead. We'll miss you buddy.";
    }


}
