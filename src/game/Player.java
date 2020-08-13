package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();
	private int money;
	private GameMap landMap;
	private GameMap waterMap;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, GameMap landMap, GameMap waterMap) {
		super(name, displayChar, hitPoints);
		money = 0;
		this.landMap = landMap;
		this.waterMap = waterMap;
	}

	/**
	 * play turn of the player, sync with each turn of the game
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return menu showing available actions
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Action
		Location currentLocation = map.locationOf(this);

		if (nextToEdgeMap(currentLocation)){
			if (map.equals(waterMap)){ //player move from water map to land map only if the position has no other actor
				Location newLocation = landMap.at(currentLocation.x(),1);
				if (!newLocation.containsAnActor()){
					actions.add(new MoveActorAction(newLocation, "to the land map!"));
				}


			}else {	//player move from land map to water map only if the position has no other actor
				Location newLocation = waterMap.at(currentLocation.x(),23);
				if (!newLocation.containsAnActor()){
					actions.add(new MoveActorAction(newLocation, "to the water map!"));
				}
			}
		}

		//add QuitGameAction into actions
		actions.add(new QuitGameAction());

		System.out.println("Your purse: $"+getMoney());
		System.out.println("Your hitpoints: "+getHitPoints());
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}

	/**
	 * return balance of money of player
	 * @return money
	 */
	public int getMoney(){
		return money;
	}

	/**
	 * return the player's hitpoints
	 * @return hitpoints
	 */
	public int getHitPoints(){
		return hitPoints;
	}

	/**
	 * check if player has sufficient money after trade(no negative balance)
	 * @param amount the amount to deduct
	 * @return true of sufficient, otherwise false
	 */
	private boolean haveEnoughMoney(int amount){
		int balance = money - amount;
		if (balance <0){
			return false;
		}
		return true;
	}

	/**
	 * play spending money
	 * @param amount amount deduct
	 * @return message to display
	 */
	public String spendMoney(int amount){
		if(haveEnoughMoney(money)) {
			money -= amount;
			return "Balance: $" + money ;
		}else{
			return "You do not have enough money to trade.";
		}
	}

	/**
	 * player earn money
	 * @param amount amount earned
	 */
	public void earnMoney(int amount){
		money += amount;
	}

	/**
	 * check if player has the item in their inventory, check by using display char of item
	 * @param displayChar the character to check for
	 * @return true if found, otherwise false
	 */
	public boolean hasItem(char displayChar){
		for (Item item : this.getInventory()){
			if (item.getDisplayChar() == displayChar){
				return true;
			}
		}
		return false;
	}

	/**
	 * get the first item of the same display character from inventory
	 * @param displayChar the character to check in inventory
	 * @return the item if found, otherwise null
	 */
	public Item getFirstItem(char displayChar){
		for (Item item: this.getInventory()){
			if (item.getDisplayChar() == displayChar){
				return item;
			}
		}
		return null;
	}

	/**
	 * check if player is standing next to the edge of the map
	 * @param location the location of player
	 * @return true if player is next to the edge of the map, otherwise false
	 */
	private boolean nextToEdgeMap(Location location){

		for(Exit exit:location.getExits()){
			if (exit.getDestination().getDisplayChar() == '^'){
				return true;
			}
		}
		return false;
	}





	// methods below here are irrelevant methods but has to be implemented due to declaration in ActorInterface

	/**
	 * does nothing, has to be implemented due to declaration in ActorInterface
	 */
	public void increaseFoodLevel(int foodPoints){}

	/**
	 * does nothing, has to be implemented due to declaration in ActorInterface
	 * player carry no value, therefore it cannot be sold
	 */
	public int getValue(){
		return 0;
	}

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
