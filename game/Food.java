package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Food objects (fish)
 */
public class Food extends DynamicBody {

    /** Shape of hit box */
    private static final Shape shape = new PolygonShape(
            -0.788f,0.428f, -0.712f,-0.424f, 0.908f,-0.248f, 0.904f,0.148f, 0.488f,0.516f, -0.18f,0.536f, -0.696f,0.448f);

    /** Image */
    private static final BodyImage image =
            new BodyImage("data/fish.png", 2);

    /**
     * Constructor to add image and shape to body
     * @param world the current level
     */
    public Food(World world) {
        super(world, shape);
        addImage(image);
    }
}
