package game;

import city.cs.engine.*;

/**
 * Damage Listener
 */
public class Damage implements CollisionListener {

    private Panda panda;
    private Pingu pingu;

    // Constructor
    /**
     * Damage listener for which characters?
     * @param panda Player 2
     * @param pingu Player 1
     */
    public Damage(Panda panda, Pingu pingu) {
        this.panda = panda;
        this.pingu = pingu;
    }

    @Override
    public void collide(CollisionEvent e) {
        // Take damage if a player touches the sharp ice
        if (e.getOtherBody() == panda) {
            panda.damage();
            e.getReportingBody().destroy();
            Sound.getPain().play();
        }
        if (e.getOtherBody() == pingu) {
            pingu.damage();
            e.getReportingBody().destroy();
            Sound.getPain().play();
        }

        // Exit the game if any player runs out of health points
        if (panda.getHP() == 0 || pingu.getHP() == 0) {
            System.exit(0);
        }
    }


}
