package game;

import city.cs.engine.*;

// Free image for metal: https://www.pngfuel.com/free-png/alplf

/**
 * Wooden barrier
 */
public class Wood extends StaticBody {

    /** Shape of hit box */
    private static final Shape shape = new PolygonShape(
            -0.96f,1.98f, 0.95f,1.97f, 0.95f,-1.97f, -0.95f,-1.96f);

    /** Image of body */
    private static final BodyImage image =
            new BodyImage("data/wood.png", 4f);

    /**
     * Constructor to add image and shape to body
     * @param world the current level
     */
    public Wood(World world) {
        super(world, shape);
        addImage(image);
    }
}