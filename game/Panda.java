package game;

import city.cs.engine.*;

/** Player 2 - Panda */

public class Panda extends Walker {

    /**
     * The hit box shape
     */
    private static final Shape shape = new PolygonShape(
            -1.069f,0.979f, 0.293f,0.739f, 1.113f,-1.111f, -0.532f,-1.092f, -1.211f,-0.359f);

    /**
     * The Image
     */
    private static final BodyImage image =
            new BodyImage("data/panda.png", 2.25f);

    /** Energy level */
    private static int energyLevel;
    /** Health points*/
    private static int healthPoints;

    // Constructor to add image and shape to body
    /**
     * Constructor to add image and shape to body
     * @param world the current level
     */
    public Panda(World world) {
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

    /** Get HP */
    public static int getHP() {
        return healthPoints;
    }

    /** Increment energy level */
    public void gainEnergy() {
        energyLevel++;
        System.out.println("Pingu has eaten a fish! Energy Level: " + energyLevel);
    }

    /** Lose HP */
    public void damage() {
        healthPoints--;
        System.out.println("Oh no! Penguin has taken damage... Remaining HP: " + healthPoints);
    }

}
