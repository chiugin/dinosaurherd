package game;

/**
 * A herbivorous dinosaur.
 */
public class Protoceratops extends Dinosaurs {

	private static final int MAX_PROTO_FOOD_LEVEL = 50;
	private static final int PROTO_HUNGER_LEVEL = 20;

	/** 
	 * Constructor.
	 * All Protoceratops are represented by a 'P' if it's an adult and have 100 hit points and starting at an age of 30
	 * @param name the name of this Protoceratops
	 */
	public Protoceratops(String name) {
		super(name, 'P', 100, 30, 30, true);
		dinoStatus = DinoStatus.ADULT;
		maxFoodLevel = MAX_PROTO_FOOD_LEVEL;
		hungryLevel = PROTO_HUNGER_LEVEL;
		value = 100;
		behaviour = new WanderBehaviour();
	}


	/**
	 * constructor for newly born baby ie. hatched from an egg
	 * baby Protoceratops are represented by a 'p' and have 100 hit points
	 * @param name name of this Protoceratops
	 * @param age starting age which should be 0
	 * @param foodLevel starting food level
	 */
	public Protoceratops(String name, int age, int foodLevel, boolean captiveBreed) {
		super(name, 'p', 100, age, foodLevel, captiveBreed);
        dinoStatus = DinoStatus.BABY;
		maxFoodLevel = MAX_PROTO_FOOD_LEVEL;
		hungryLevel = PROTO_HUNGER_LEVEL;
		value = 100;
		behaviour = new HungryBehaviour();
	}


}
