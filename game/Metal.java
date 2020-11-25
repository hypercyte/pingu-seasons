package game;

import city.cs.engine.*;

// Free image for metal: https://www.pngfuel.com/free-png/alplf

/**
 * Metal barrier
 */
public class Metal extends StaticBody {

    /** Shape of hit box */
    private static final Shape shape = new PolygonShape(
            -0.96f,1.98f, 0.95f,1.97f, 0.95f,-1.97f, -0.95f,-1.96f);

    /** Image of body */
    private static final BodyImage image =
            new BodyImage("data/metal.png", 3.8f);

    /**
     * Constructor to add image and shape to body
     * @param world the current level
     */
    public Metal(World world) {
        super(world, shape);
        addImage(image);
    }
}