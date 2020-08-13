package game;

import edu.monash.fit2099.engine.IntrinsicWeapon;

/**
 * a large carnivorous dinosaur
 */
public class Trex extends Dinosaurs {

    private static final int MAX_TREX_FOOD_LEVEL = 500;
    private static final int TREX_HUNGER_LEVEL = 350;

    /**
     * Constructor.
     * All T-Rex are represented by a 'R' if it's an adult and have 300 hit points and starting at an age of 30
     * @param name the name of this T-Rex
     */
    public Trex (String name) {
        super(name, 'R', 300, 30, 70 , true);
        dinoStatus = DinoStatus.ADULT;
        maxFoodLevel = MAX_TREX_FOOD_LEVEL;
        hungryLevel = TREX_HUNGER_LEVEL;
        value = 100000;
        behaviour = new WanderBehaviour();
    }

    /**
     * constructor for newly born baby ie. hatched from an egg
     * baby T-Rex are represented by a 'r' and have 100 hit points
     * @param name name of this T-Rex
     * @param age starting age which should be 0
     * @param foodLevel starting food level
     */
    public Trex(String name, int age, int foodLevel, boolean captiveBreed) {
        super(name, 'r', 100, age, foodLevel, captiveBreed);
        dinoStatus = DinoStatus.BABY;
        maxFoodLevel = MAX_TREX_FOOD_LEVEL;
        hungryLevel = TREX_HUNGER_LEVEL;
        value = 100;
        behaviour = new HungryBehaviour();
    }


    @Override
    // make T-Rex attack gives more damage
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "attacks");
    }
}
