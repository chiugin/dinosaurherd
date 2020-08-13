package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * an action to sell item
 */
public class SellAction extends Action {

    private Item item;

    /**
     * Constructor
     * @param item the item to be sold
     */
    public SellAction(Item item){
        this.item = item;
    }

    /**
     * execute the action by removing the item from inventory and adding money to actor4
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of action
     */
    public String execute(Actor actor, GameMap map){
        actor.removeItemFromInventory(item);
        actor.earnMoney(item.getSellValue());

        return menuDescription(actor);
    }

    /**
     * description of action
     * @param actor The actor performing the action.
     * @return string indicating the action performed
     */
    public String menuDescription(Actor actor){
        return actor + " sells " + item + " for $" + item.getSellValue();
    }
}
