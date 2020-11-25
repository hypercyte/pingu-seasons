package game;

import city.cs.engine.*;

// Penguin image free for personal use: https://www.pngguru.com/free-transparent-background-png-clipart-uxrmp

/**
 * Skunk - Enemy character
 */

public class Skunk extends Walker {

    /** Shape of hit box */
    private static final Shape shape = new PolygonShape(
            -0.852f,0.189f, 0.792f,0.605f, 0.963f,0.253f, 0.586f,-0.587f, -0.82f,-0.599f);

    /** Image of body */
    private static final BodyImage image =
            new BodyImage("data/skunk.png", 1.25f);

    /**
     * Constructor to add image and shape to body
     * @param world the current level
     */
    public Skunk(World world) {
        super(world, shape);
        addImage(image);
    }

}