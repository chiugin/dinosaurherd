package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * an action for feeding
 */
public class FeedAction extends Action {


    private Actor target;
    private Item item;

    /**
     * Constructor
     * @param item item that is going to be fed
     * @param target the target to feed to
     */
    public FeedAction(Item item, Actor target){
        this.target = target;
        this.item = item;
    }

    /**
     * execute this action (feeding) by removing item from actor's inventory and increasing foodLevel of target
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of this action
     */
    public String execute(Actor actor, GameMap map){
        actor.removeItemFromInventory(item);
        target.increaseFoodLevel(item.getFoodPoints());

        return menuDescription(actor);
    }

    /**
     * Description of this action
     * @param actor The actor performing the action.
     * @return string indicating this action
     */
    public String menuDescription(Actor actor){
        return actor + " feeds " + target;
    }

}
