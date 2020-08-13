package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {

    public void increaseFoodLevel(int foodPoints);

    public int getFoodPoints();

    public int getValue();

    public int getMoney();

    public void earnMoney(int amount);

    public String spendMoney (int amount);

    public boolean isCaptiveBreed();

}
