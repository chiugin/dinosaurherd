package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * an action for buying item in shop
 */
public class BuyAction extends Action {

    private Item item; //the item being bought by Player

    /**
     * constructor
     * @param item item that the actor decides to buy
     */
    public BuyAction(Item item){
        this.item = item;
    }

    /**
     * execute buying action by adding item into actor's inventory and spends actor's money
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return return description of this action
     */
    public String execute(Actor actor, GameMap map){
        actor.addItemToInventory(item);
        actor.spendMoney(item.getBuyValue());

        return menuDescription(actor);
    }

    /**
     * Description of this class (buying action)
     * @param actor The actor performing the action.
     * @return string indicating trade of item
     */
    public String menuDescription(Actor actor){
        return actor + " buys " + item + " for $" + item.getBuyValue();
    }
}
