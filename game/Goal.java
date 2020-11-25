package game;

import city.cs.engine.*;

// Goal image free: https://www.pngfuel.com/free-png/csmij

/**
 * The goal, gate to the next level.
 */
public class Goal extends StaticBody {

    /** Shape of hit box */
    private static final Shape shape = new PolygonShape(
            -0.404f,-0.98f, -1.008f,0.704f, -0.072f,1.016f, 0.972f,0.992f, 0.984f,-0.236f);

    /** Image of body */
    private static final BodyImage image =
            new BodyImage("data/goal.png", 2);

    /**
     * Constructor to add image and shape to body
     * @param world the current level
     */
    public Goal(World world) {
        super(world, shape);
        addImage(image);
    }
}