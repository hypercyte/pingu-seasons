package game;

import city.cs.engine.*;

/** Player 1 - Pingu */

public class Pingu extends Walker {

    /**
     * The hit box shape
     */
    private static final Shape shape = new PolygonShape(
            -1.066f,-0.665f, -0.886f,0.55f, -0.018f,1.018f, 0.486f,0.55f, 0.36f,-0.89f, -0.589f,-0.966f);

    /**
     * The Image
     */
    private static final BodyImage image =
            new BodyImage("data/penguin.png", 2);

    /** Energy level */
    private static int energyLevel;
    /** Health points*/
    private static int healthPoints;

    // Constructor to add image and shape to body

    /**
     * Constructor to add image and shape to body
     * @param world the current level
     */
    public Pingu(World world) {
        super(world, shape);
        addImage(image);
        energyLevel = 0;
        healthPoints = 2;
    }

    // Getter and setters
    /** Get energy level */
    public static int getEnergyLevel() {
        return energyLevel;
    }

    /** Get HP level */
    public static int getHP() {
        return healthPoints;
    }

    /** Increment energy number */
    public void gainEnergy() {
        energyLevel++;
        System.out.println("Pingu has eaten a fish! Energy Level: " + energyLevel );
    }

    /** Lose HP */
    public void damage() {
        healthPoints--;
        System.out.println("Oh no! Penguin has taken damage... Remaining HP: " + healthPoints);
    }

}
