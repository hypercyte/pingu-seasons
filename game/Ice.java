package game;

import city.cs.engine.*;

// Ice image drawn/created by me
/** Ice object */

public class Ice extends StaticBody {

    // Shape of hit box
    /**
     * The hit box shape
     */
    private static final Shape shape = new PolygonShape(
            -0.888f,-0.996f, -0.452f,0.556f, 0.132f,0.544f, 0.672f,0.196f, 0.94f,-1.008f);

    // Image of body
    /**
     * The Image
     */
    private static final BodyImage image =
            new BodyImage("data/ice_spike.png", 2);

    /**
     * Constructor to add image and shape to body
     * @param world the current level
     */
    public Ice(World world) {
        super(world, shape);
        addImage(image);
    }
}