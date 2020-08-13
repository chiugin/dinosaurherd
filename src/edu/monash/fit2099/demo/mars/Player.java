package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();
	
	/**
	 * Constructor.
	 *
	 * @param name Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		return menu.showMenu(this, actions, display);
	}

	public void increaseFoodLevel(int foodPoints){}

	public int getMoney(){return 0;}

	public void earnMoney(int amount){}

	public String spendMoney (int amount){return null;}

	public int getValue(){return 0;}

	/**
	 * does nothing, has to be implemented due to declaration in ActorInterface
	 * player carry no food point, therefore it cannot be eaten
	 */
	public int getFoodPoints() {return 0;}

	/**
	 * does nothing, has to be implemented due to declaration in ActorInterface
	 */
	public boolean isCaptiveBreed(){return true;}
}
