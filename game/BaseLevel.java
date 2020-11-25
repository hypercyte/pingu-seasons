package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * The base level template which the levels inherit from.
 */
public abstract class BaseLevel extends World {

    // Fields
    private Pingu pingu;
    private Panda panda;

    /** Get player 1 */
    // Getters for the players
    public Pingu getPlayer1() {
        return pingu;
    }
    /** Get player 2 */
    public Panda getPlayer2() {
        return panda;
    }

    /**
     * Creating the world
     * @param game The game
     */
    public void create(Game game) {

        // Creating the "Penguin" (Player 1)
        pingu = new Pingu(this);
        pingu.setPosition(pinguPos());

        // Creating the "Panda" (Player 2)
        panda = new Panda(this);
        panda.setPosition(pandaPos());

        // Goal point (Finish level)
        Body goal = new Goal(this);
        goal.setPosition(goalPos());
        goal.addCollisionListener(new GoalListener(game));
    }

    // Overrides

    /** Initial Position of Player 1 (Penguin) */
    public abstract Vec2 pinguPos();

    /** Initial Position of Player 2 (Panda) */
    public abstract Vec2 pandaPos();

    /** Portal Gate Position (In order to proceed to the next level) */
    public abstract Vec2 goalPos();

    /** Level completion validator */
    public abstract boolean completed();

    /** Report which level we are on */
    public abstract int levelNumber();
}