package game;

import edu.monash.fit2099.engine.*;

/**
 * the class to looks overs all of the maps and the actors on it
 */
public class GameWorld extends World {

    private boolean win;

    /**
     * constructor
     * @param display display
     */
    public GameWorld(Display display) {
        super(display);
        win = false;
    }


    /**
     * Run the game.
     *
     * On each iteration the gameloop does the following: - displays the player's
     * map - processes the actions of every Actor in the game, regardless of map
     *
     * We could either only process the actors on the current map, which would make
     * time stop on the other maps, or we could process all the actors. We chose to
     * process all the actors.
     *
     * @throws IllegalStateException if the player doesn't exist
     */
    public void run() {
        if (player == null)
            throw new IllegalStateException();

        // initialize the last action map to nothing actions;
        for (Actor actor : actorLocations) {
            lastActionMap.put(actor, new DoNothingAction());
        }

        // This loop is basically the whole game
        while (stillRunning()) {
            GameMap playersMap = actorLocations.locationOf(player).map(); //get the gamemap of the Player
            playersMap.draw(display); //prints the current map

            // Process all the actors.
            for (Actor actor : actorLocations) {
                if (stillRunning())
                    if (actor instanceof Fish){
                        Location here = actorLocations.locationOf(actor);
                        GameMap map = here.map();
                        Actions actions = new Actions();
                        Action action = actor.playTurn(actions, lastActionMap.get(actor), map, display);
                        lastActionMap.put(actor, action);
                        action.execute(actor, map);
                    } else {
                        processActorTurn(actor);
                    }

            }

            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                gameMap.tick();
            }

            if (stillRunning()){
                checkWin();
            }

        }
        display.println(endGameMessage());
    }

    /**
     * check if the player wins the game (successfully raised a captive-bred T-Rex to Adulthood
     */
    private void checkWin(){
        GameMap playerMap = actorLocations.locationOf(player).map();
        if (numberOfCaptiveBreedTrex()>0){
            playerMap.removeActor(player);
            win = true;
        }
    }

    /**
     * check if there are any captive-bred Adult T-rex
     * @return
     */
    private int numberOfCaptiveBreedTrex(){
        int countTrex = 0 ;
        for (Actor actor : actorLocations){
            if (actor instanceof Trex){
                if (actor.isCaptiveBreed() && ((Trex) actor).dinoStatus == Dinosaurs.DinoStatus.ADULT){
                    countTrex++;
                }
            }
        }
        return countTrex;
    }

    /**
     * Return a string that can be displayed when the game ends.
     *
     * @return the string "Player wins" if player wins else "Player loses"
     */
    @Override
    protected String endGameMessage() {
        if (win){
            return "Congrats on breeding and raising your T-rex to adulthood! Player wins!";
        }
        return "Game Over";
    }





}
