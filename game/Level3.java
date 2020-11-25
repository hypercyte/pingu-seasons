package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * Level 3
 */
public class Level3 extends BaseLevel {

    private static final int FOODCOUNT = 6;

    public static int getFOODCOUNT() {
        return FOODCOUNT;
    }

    // Create level
    @Override
    public void create(Game game) {
        super.create(game);

        // ground
        Shape groundShape = new BoxShape(44, .5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setFillColor(new Color(221, 84, 0));
        ground.setPosition(new Vec2(0, -.5f));

        Shape groundShape2 = new BoxShape( 12, .5f );
        Body ground2 = new StaticBody(this, groundShape2);
        ground2.setFillColor(new Color(221, 84, 0));
        ground2.setPosition(new Vec2(23, 24.5f));

        Shape undergroundShape = new BoxShape(44, 44f);
        Body underground = new StaticBody(this, undergroundShape);
        underground.setFillColor(new Color(112, 77, 20));
        underground.setPosition(new Vec2(0, -45f));

        // walls
        Shape leftWallShape = new BoxShape(0.5f, 24, new Vec2(-11.5f, 24.5f));
        Fixture leftWall = new SolidFixture(ground, leftWallShape);
        Shape rightWallShape = new BoxShape(0.5f, 12, new Vec2(11.5f, 12.5f));
        Fixture rightWall = new SolidFixture(ground, rightWallShape);
        Fixture rightWallExt = new SolidFixture(ground2, rightWallShape);

        // platforms
        Shape boxShape = new BoxShape(3, 0.5f);
        Body platform1 = new StaticBody(this, boxShape);
        platform1.setPosition(new Vec2(-8, 4.5f));
        Body platform2 = new StaticBody(this, boxShape);
        platform2.setPosition(new Vec2(3, 8.5f));
        Body platform3 = new StaticBody(this, boxShape);
        platform3.setPosition(new Vec2(-8, 12.5f));
        Body platform4 = new StaticBody(this, new BoxShape(1.5f, 0.5f));
        platform4.setPosition(new Vec2(-9.5f, 16.5f));
        Body platform5 = new StaticBody(this, new BoxShape(0.75f, 0.5f));
        platform5.setPosition(new Vec2(-10.25f, 20.5f));
        Body platform6 = new StaticBody(this, new BoxShape(7, 0.5f, new Vec2(0,0), 35));
        platform6.setPosition(new Vec2(5, 21.6f));
        Body platform7 = new StaticBody(this, new BoxShape(7, 0.5f));
        platform7.setPosition(new Vec2(-5, 24.5f));

        // Food generation
        for (int i = 0; i < FOODCOUNT; i++) {
            Body food = new Food(this);
            food.setPosition(new Vec2(i*6 - 8, i*14));
            food.addCollisionListener(new Pickup(getPlayer2(), getPlayer1()));
        }

        Sound.getFall().loop();
    }




    // Override methods
    @Override
    public Vec2 pinguPos() {
        return new Vec2(-10, 0);
    }

    @Override
    public Vec2 pandaPos() {
        return new Vec2(10, 0);
    }

    @Override
    public Vec2 goalPos() {
        return new Vec2(30,26);
    }

    @Override
    public boolean completed() {
        return (getPlayer1().getEnergyLevel() + getPlayer2().getEnergyLevel()) == FOODCOUNT;
    }

    @Override
    public int levelNumber() {
        return 3;
    }

    @Override
    public void stop() {
        super.stop();
        Sound.getFall().stop();
    }
}
