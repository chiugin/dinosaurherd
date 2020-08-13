package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
/**
 * shop on the map for trading of items
 */
public class Shop extends Ground {

    /**
     * Constructor
     */
    public Shop(){super('S');}


    /**
     * Return actions other Actor (Player) may perform when standing next to Player
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions that is valid
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){

        Actions actions = new Actions();

        if (actor instanceof Player){

            //initialise list of items that is sold by Shop
            ArrayList<Item> sellingItems = new ArrayList<Item>();
            sellingItems.add(new PortableDinoItem("Herbivore food", 'h', 20,0, true, DinoType.PROTO, 50));
            sellingItems.add(new PortableDinoItem("Carnivore food", 'c', 100,0, true, DinoType.VELO, 100));
            sellingItems.add(new PortableDinoItem("Marine food", 'm', 200,0, true, DinoType.PLESIO, 100));
            sellingItems.add(new Egg("Protoceratops egg", 'o', 50,10, true, DinoType.PROTO, 10, 0, false));
            sellingItems.add(new Egg("Velociraptors egg", 'o', 1000,200, true, DinoType.VELO, 10, 0, false));
            sellingItems.add(new Egg("Plesiosaurs egg", 'o', 6000,1200, true, DinoType.PLESIO, 10, 0, false));
            sellingItems.add(new Egg("Pteanodons egg",'o',15000,3000,true,DinoType.PTEANO,10,0, false));
            sellingItems.add(new Egg("T-Rex egg",'o',150000,7000,true,DinoType.TREX,10,0, false));
            sellingItems.add(new PortableDinoItem("dinosaur tag", '/', 0,0, false));

            //first loop for items that can be bought
            for (Item item: sellingItems){
                if (actor.getMoney() >= item.getBuyValue()){
                    actions.add(new BuyAction(item));
                }
            }

            //second loop for item in inventory to sell
            for (Item item : actor.getInventory()){
                if (item.getSellValue() != 0){
                    actions.add(new SellAction(item));
                }
            }

        }


        return actions;
    }

    /**
     * actor cannot be on the same position as the shop
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * object is blocked as object cannot be put on shop
     * @return true (it is blocked)
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }




    /**
     * does nothing, has to be implemented due to declaration in ActorInterface
     * shop does not carry any food point as it cannot be eaten
     * @return 0
     */
    public int getFoodPoints(){return 0;}
}
