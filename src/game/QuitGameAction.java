package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * a player quits the game manually
 */
public class QuitGameAction extends Action {


    public String execute(Actor actor, GameMap map){
        map.removeActor(actor);
        return menuDescription(actor);
    }


    public String menuDescription(Actor actor){
        return actor + " quits game";
    }

}
