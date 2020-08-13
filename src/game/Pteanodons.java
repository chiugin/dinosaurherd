package game;

/**
 * a flying carnivorous dinosaur
 */
public class Pteanodons extends Dinosaurs {

    private static final int MAX_PTEANO_FOOD_LEVEL = 150;
    private static final int PTEANO_HUNGER_LEVEL = 60;


    /**
     * Constructor.
     * All Pteanodons are represented by a 'F' if it's an adult and have 100 hit points and starting at an age of 30
     * @param name the name of this Pteanodon
     */
    public Pteanodons (String name) {
        super(name, 'F', 100, 30, 30 , true);
        dinoStatus = DinoStatus.ADULT;
        maxFoodLevel = MAX_PTEANO_FOOD_LEVEL;
        hungryLevel = PTEANO_HUNGER_LEVEL;
        value = 50000;

        // make it Wander up to 2 steps
        behaviour = new WanderBehaviour();
    }

    /**
     * constructor for newly born baby ie. hatched from an egg
     * baby Pteanodons are represented by a 'f' and have 100 hit points
     * @param name name of this Pteanodons
     * @param age starting age which should be 0
     * @param foodLevel starting food level
     */
    public Pteanodons(String name, int age, int foodLevel, boolean captiveBreed) {
        super(name, 'f', 100, age, foodLevel, captiveBreed);
        dinoStatus = DinoStatus.BABY;
        maxFoodLevel = MAX_PTEANO_FOOD_LEVEL;
        hungryLevel = PTEANO_HUNGER_LEVEL;
        value = 400;

        // moves 2 steps to move if can (distance more than 1) or else moves one steps
        behaviour = new HungryBehaviour();
    }

}
