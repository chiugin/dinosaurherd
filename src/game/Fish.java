package game;

import edu.monash.fit2099.engine.*;

/**
 * class to instantiate fish object
 */
public class Fish extends Actor {

    private int age;
    private int foodPoints;

    /***
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
	public Fish(String name, char displayChar, int hitPoints) {
            super(name, displayChar, hitPoints);
            age = 0;
            foodPoints = 10;
        }

    /**
     * play turn of the fish
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action of fish
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        age++;
        if (age>20){
            return new DieAction();
        }

        //fish wanders around at random
        Behaviour behaviour = new WanderBehaviour();
        Action behaviourAction = behaviour.getAction(this, map);
        if (behaviourAction != null){
            return behaviourAction;
        }

        return new DoNothingAction();

    }

    /**
     * get the food point gain from eating a fish
     * @return the food points
     */
    public int getFoodPoints(){
        return foodPoints;
    }






    // methods below here are irrelevant methods but has to be implemented due to declaration in ActorInterface

    /**
     * does nothing, has to be implemented due to declaration in ActorInterface
     */
    public void increaseFoodLevel(int foodPoints){}

    /**
     * does nothing, has to be implemented due to declaration in ActorInterface
     * player carry no food point, therefore it cannot be eaten
     */
    public int getValue(){
        return 0;
    }

    /**
     * does nothing, has to be implemented due to declaration in ActorInterface
     */
    public int getMoney(){return 0;}

    /**
     * does nothing, has to be implemented due to declaration in ActorInterface
     */
    public void earnMoney(int amount){}

    /**
     * does nothing, has to be implemented due to declaration in ActorInterface
     */
    public String spendMoney (int amount){return null;}

    /**
     * does nothing, has to be implemented due to declaration in ActorInterface
     */
    public boolean isCaptiveBreed(){return true;}
}
