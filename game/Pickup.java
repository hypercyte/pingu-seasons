package game;

import city.cs.engine.*;

/**
 * Food pickup Listener
 */

public class Pickup implements CollisionListener {
    private Panda panda;
    private Pingu pingu;


    /**
     * Pickup listener for which characters?
     * @param panda Player 2
     * @param pingu Player 1
     */
    public Pickup(Panda panda, Pingu pingu) {
        this.panda = panda;
        this.pingu = pingu;
    }

    @Override
    public void collide(CollisionEvent e) {

            if (e.getOtherBody() == panda){
                panda.gainEnergy();
                e.getReportingBody().destroy();
                Sound.getNom().play();
            } else if (e.getOtherBody() == pingu) {
                pingu.gainEnergy();
                e.getReportingBody().destroy();
                Sound.getNom().play();
            }

    }

    
}
