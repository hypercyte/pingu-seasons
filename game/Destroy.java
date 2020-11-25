package game;

import city.cs.engine.*;

/**
 * Destroyable bodies
 */

public class Destroy implements CollisionListener {
    private Panda panda;
    private Pingu pingu;

    public Destroy(Panda panda, Pingu pingu) {
        this.panda = panda;
        this.pingu = pingu;
    }

    @Override
    public void collide(CollisionEvent e) {
        // Destroy a perishable block when colliding ONLY if said player(s) have enough energy.

        // WOOD OBJECTS
        // If panda collides, and both players have more than 2 energy, destroy block.
        if (e.getReportingBody() instanceof Wood && e.getOtherBody() == panda && (panda.getEnergyLevel() + pingu.getEnergyLevel()) >= 2) {
            e.getReportingBody().destroy();
            System.out.println("The panda had enough energy to break down the wood barrier!");
            Sound.getSmash().play();
        }
        // If pingu collides, and both players have more than 2 energy, destroy block.
        else if (e.getReportingBody() instanceof Wood && e.getOtherBody() == pingu && (panda.getEnergyLevel() + pingu.getEnergyLevel()) >= 2) {
            e.getReportingBody().destroy();
            System.out.println("The penguin had enough energy to break down the wood barrier!");
            Sound.getSmash().play();
        }
        else if (e.getReportingBody() instanceof Wood) {
            System.out.println("One player needs 2 energy points in order to take this down.");
        }

        // METALLIC OBJECTS
        // If either player collides, and both have 4 or more energy, destroy block.
        if (e.getReportingBody() instanceof Metal && (e.getOtherBody() == pingu || e.getOtherBody() == panda) && (pingu.getEnergyLevel() + panda.getEnergyLevel() >= 4)) {
            e.getReportingBody().destroy();
            System.out.println("The metal barrier has been taken down! You may now proceed to the next level!");
            Sound.getSmash().play();
        }
        else if (e.getReportingBody() instanceof Metal) {
            System.out.println("Both players needs 2 energy points (total of 4) in order to take this down.");
        }
    }
}
