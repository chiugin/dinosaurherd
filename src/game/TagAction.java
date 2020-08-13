package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * an action of tagging actor (for trading in shop)
 */
public class TagAction extends Action {

    private Actor target;
    private Item item;


    /**
     * Constructor
     * @param item the tag
     * @param target the actor to be tagged
     */
    public TagAction(Item item, Actor target){
        this.target = target;
        this.item = item;
    }

    /**
     * execute the action (tagging) by removing the dinosaur tag from inventory and removing the target actor that is tagged from map
     * Actor performing the tag action earns money
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of action
     */
    public String execute(Actor actor, GameMap map){
        actor.removeItemFromInventory(item);
        map.removeActor(target);
        actor.earnMoney(target.getValue());

        return menuDescription(actor);
    }

    /**
     * Description of action
     * @param actor The actor performing the action.
     * @return string indicating the action performed
     */
    public String menuDescription(Actor actor){
        return actor + " tags " + target;
    }
}
