package game;

/**
 * A carnivorous dinosaur.
 */
public class Velociraptors extends Dinosaurs {


    private static final int MAX_VELO_FOOD_LEVEL = 100;
    private static final int VELO_HUNGER_LEVEL = 50;


    /**
     * Constructor.
     * All Velociraptors are represented by a 'V' if it's an adult and have 100 hit points and starting at an age of 30
     * @param name the name of this Velociraptors
     */
    public Velociraptors (String name) {
        super(name, 'V', 100, 30, 70 , true);
        dinoStatus = DinoStatus.ADULT;
        maxFoodLevel = MAX_VELO_FOOD_LEVEL;
        hungryLevel = VELO_HUNGER_LEVEL;
        value = 1200;
        behaviour = new WanderBehaviour();
    }


    /**
     * constructor for newly born baby ie. hatched from an egg
     * baby Velociraptors are represented by a 'v' and have 100 hit points
     * @param name name of this Velociraptors
     * @param age starting age which should be 0
     * @param foodLevel starting food level
     */
    public Velociraptors(String name, int age, int foodLevel, boolean captiveBreed) {
        super(name, 'v', 100, age, foodLevel, captiveBreed);
        dinoStatus = DinoStatus.BABY;
        maxFoodLevel = MAX_VELO_FOOD_LEVEL;
        hungryLevel = VELO_HUNGER_LEVEL;
        value = 100;
        behaviour = new HungryBehaviour();
    }

}
