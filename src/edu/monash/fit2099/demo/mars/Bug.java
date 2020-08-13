package edu.monash.fit2099.demo.mars;

import java.util.*;

import game.Behaviour;
import edu.monash.fit2099.engine.*;


public class Bug extends Actor {

	private Random rand = new Random();
	public List<Behaviour> actionFactories = new ArrayList<Behaviour>();

	public Bug() {
		super("Feature", 'x', 1);
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return actions.get(rand.nextInt(actions.size()));
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.add(new KickAction(this));
		return list;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(1, "stings");
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
