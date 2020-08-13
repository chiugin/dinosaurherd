package game;

/**
 * a marine carnivorous dinosaur.
 */
public class Plesiosaurs extends Dinosaurs {

    private static final int MAX_PLESIO_FOOD_LEVEL = 100;
    private static final int PLESIO_HUNGER_LEVEL = 60;


    /**
     * Constructor.
     * All Protoceratops are represented by a 'Q' if it's an adult and have 100 hit points and starting at an age of 30
     * @param name the name of this Plesiosaurs
     */
    public Plesiosaurs(String name) {
        super(name, 'Q', 100, 30, 70, true );
        dinoStatus = DinoStatus.ADULT;
        maxFoodLevel = MAX_PLESIO_FOOD_LEVEL;
        hungryLevel = PLESIO_HUNGER_LEVEL;
        value = 12000;
        behaviour = new WanderBehaviour();
    }


    /**
     * constructor for newly born baby ie. hatched from an egg
     * baby Plesiosaurs are represented by a 'q' and have 100 hit points
     * @param name name of this Plesiosaurs
     * @param age starting age which should be 0
     * @param foodLevel starting food level
     */
    public Plesiosaurs(String name, int age, int foodLevel, boolean captiveBreed) {
        super(name, 'q', 100, age, foodLevel, captiveBreed);
        dinoStatus = DinoStatus.BABY;
        maxFoodLevel = MAX_PLESIO_FOOD_LEVEL;
        hungryLevel = PLESIO_HUNGER_LEVEL;
        value = 100;
        behaviour = new HungryBehaviour();
    }




}
