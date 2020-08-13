package game;

import edu.monash.fit2099.engine.*;

/**
 * class for dinosaur which extends Actor
 */
public abstract class Dinosaurs extends Actor {


    protected int foodLevel; // food level of Dinosaur
    protected int maxFoodLevel; //maximum food level that can be achieved by a dinosaur
    protected int hungryLevel; //the level to indicate the hunger level of this dinosaurs
    protected int age; // age of this dinosaur, used for growth of baby dinosaur
    protected DinoStatus dinoStatus; // enum indicating if a dinosaur is BABY or ADULT
    protected int value; // the selling value of this dinosaur
    protected static final int ADULT_AGE = 30; // after 30 turns, a baby dino will turn into adult
    protected Behaviour behaviour; //behaviour of dinosaur
    protected boolean captiveBreed;

    protected enum DinoStatus {BABY , ADULT}

    /**
     * Constructor
     * @param name name of the dinosaur
     * @param displayChar display character on output
     * @param hitPoints hitpoint of dinosaur
     * @param age age of dinosaur
     * @param foodLevel food level of dinosaur
     */
    public Dinosaurs(String name, char displayChar, int hitPoints, int age, int foodLevel, boolean captiveBreed){
        super(name, displayChar, hitPoints);
        this.age = age;
        this.foodLevel = foodLevel;
        this.captiveBreed = captiveBreed;
    }


    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing the action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return  a collection of actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {

        Actions actions = new Actions();
        if (otherActor instanceof Player) {
            if (((Player) otherActor).hasItem('m') && (this instanceof Plesiosaurs || this instanceof Pteanodons)) {
                if (this.isHungry()) {
                    actions.add(new FeedAction(((Player) otherActor).getFirstItem('m'), this));
                }
            }
            if (((Player) otherActor).hasItem('h') && this instanceof Protoceratops) {
                if (this.isHungry()) {
                    actions.add(new FeedAction(((Player) otherActor).getFirstItem('h'), this));
                }
            }
            if (((Player) otherActor).hasItem('c') && (this instanceof Velociraptors || this instanceof Trex || this instanceof Pteanodons) ){
                if (this.isHungry()) {
                    actions.add(new FeedAction(((Player) otherActor).getFirstItem('c'), this));
                }
            }

            if (this.isHealthy() && dinoStatus == DinoStatus.ADULT) {
                if (((Player) otherActor).hasItem('/')) {
                    actions.add(new TagAction(((Player) otherActor).getFirstItem('/'), this));
                }
            }

        }
        return actions;
    }



    /**
     * Return an action that should be taken by dinosaurs in this turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        Location actorLocation = map.locationOf(this);

        age++;
        //status of baby dinosaur turns into adult after 30 turns
        if (age >= ADULT_AGE && dinoStatus == DinoStatus.BABY){
            dinoStatus = DinoStatus.ADULT;
            displayChar = upperCase(getDisplayChar());
            display.println("Baby " + this.name + " has grown up into an adult! at ("+actorLocation.x()+","+actorLocation.y()+")");
        }


        foodLevel--;
        //if food level reaches 0, dinosaur dies
        if (foodLevel == 0) {
            display.println(this + "at (" + actorLocation.x() + "," + actorLocation.y() + ") has died of hunger :( ");
            return new DieAction();
        }


        else if (foodLevel <= hungryLevel){
            behaviour = new HungryBehaviour();
            display.println(this + " at (" + actorLocation.x() + "," + actorLocation.y() + ") is getting hungry!");

        }

        else {
            //only adult dinosaurs can breed
            if (dinoStatus == DinoStatus.ADULT){
                double breedChance = Math.random();
                if (breedChance <= 0.25){
                    return new BreedAction();
                }
            }

            behaviour = new WanderBehaviour();
        }


        Action behaviourAction = behaviour.getAction(this, map);
        if (behaviourAction != null){
            return behaviourAction;
        }

        return new DoNothingAction();

    }


    /**
     * @param ch the character to be changed to upper case
     * @return an uppercase of the character
     */
    private char upperCase(char ch){
        return Character.toUpperCase(ch);
    }

    /**
     * increase the food level of dinosaur
     * @param foodPoints the food points to add to the current food level
     */
    public void increaseFoodLevel(int foodPoints){
        foodLevel += foodPoints;
        foodLevel = Math.min(foodLevel, maxFoodLevel);

    }

    /**
     * check if dinosaur is healthy by ensuring dino is not hungry and hitPoints is not less than 50
     * @return true if healthy, otherwise false
     */
    public boolean isHealthy() {
        return foodLevel > hungryLevel && hitPoints >= 50;
    }

    /**
     * check to see if dinosaur is hungry
     * @return true if hungry, otherwise false
     */
    public boolean isHungry(){
        return foodLevel <= hungryLevel;
    }


    /**
     * get the selling value of this dinosaur
     * @return the selling value
     */
    public int getValue(){
        return value;
    }


    /**
     * Creates and returns an intrinsic weapon.
     * @return a freshly-instantiated IntrinsicWeapon
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "attacks");
    }


    public boolean isCaptiveBreed(){
        return captiveBreed;
    }


    // methods below here are irrelevant methods but has to be implemented due to declaration in ActorInterface

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
    public int getFoodPoints(){return 0;}

}
