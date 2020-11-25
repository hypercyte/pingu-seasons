package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * Level 2
 */
public class Level2 extends BaseLevel {

    private static final int FOODCOUNT = 8;

    private Skunk skunk;

    public static int getFOODCOUNT() {
        return FOODCOUNT;
    }

    // Create level
    @Override
    public void create(Game game) {
        super.create(game);

        // ground
        Shape groundShape = new BoxShape(78, .5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setFillColor(new Color(31, 186, 105));
        ground.setPosition(new Vec2(0, -.5f));

        Shape undergroundShape = new BoxShape(78, 44f);
        Body underground = new StaticBody(this, undergroundShape);
        underground.setFillColor(new Color(112, 70, 33));
        underground.setPosition(new Vec2(0, -45f));

        // walls
        Shape leftWallShape = new BoxShape(0.5f, 12, new Vec2(-77.5f, 12.5f));
        Fixture leftWall = new SolidFixture(ground, leftWallShape);
        Shape rightWallShape = new BoxShape(0.5f, 12, new Vec2(77.5f, 12.5f));
        Fixture rightWall = new SolidFixture(ground, rightWallShape);

        // platforms
        // Right side of map (active)
        Shape boxShape = new BoxShape(3, 0.5f);
        Body platform1 = new StaticBody(this, boxShape);
        platform1.setPosition(new Vec2(-8, 4.5f));
        Body platform2 = new StaticBody(this, boxShape);
        platform2.setPosition(new Vec2(3, 8.5f));
        Body platform3 = new StaticBody(this, boxShape);
        platform3.setPosition(new Vec2(14, 3.5f));
        Body platform4 = new StaticBody(this, boxShape);
        platform4.setPosition(new Vec2(25, 5.5f)); // enemy platform
        Body platform5 = new StaticBody(this, boxShape);
        platform5.setPosition(new Vec2(14, 12.5f));
        Body platform6 = new StaticBody(this, boxShape);
        platform6.setPosition(new Vec2(-19, 8.5f));
        Body platform7 = new StaticBody(this, boxShape);
        platform7.setPosition(new Vec2(36, 8.5f)); // enemy platform

        // Left side of map
        Body platform8 = new StaticBody(this, boxShape);
        platform8.setPosition(new Vec2(-30, 4.5f));
        Body platform9 = new StaticBody(this, boxShape);
        platform9.setPosition(new Vec2(-40, 8.5f));
        Body platform11 = new StaticBody(this, boxShape);
        platform11.setPosition(new Vec2(-50, 4.5f));
        Body platform12 = new StaticBody(this, boxShape);
        platform12.setPosition(new Vec2(-60, 8.5f));
        Body platform13 = new StaticBody(this, boxShape);
        platform13.setPosition(new Vec2(-70, 4.5f));

        // Creating break-able barriers.
        // -- Wood requires 2 energy to break.
        // -- Metal requires 4 energy to break (across both players)
        Body wood = new Wood(this);
        wood.setPosition(new Vec2(60, 2));
        wood.addCollisionListener(new Destroy(getPlayer2(), getPlayer1()));
        Body wood2 = new Wood(this);
        wood2.setPosition(new Vec2(60, 6));
        wood2.addCollisionListener(new Destroy(getPlayer2(), getPlayer1()));
        Body wood3 = new Wood(this);
        wood3.setPosition(new Vec2(60, 10));
        wood3.addCollisionListener(new Destroy(getPlayer2(), getPlayer1()));

        Shape roofShape = new BoxShape(9, 0.5f);
        Body roof = new StaticBody(this, roofShape);
        roof.setPosition(new Vec2(68, 12.5f));

        // Food generation
        for (int i = 0; i < FOODCOUNT; i++) {
            Body food = new Food(this);
            food.setPosition(new Vec2(i*11 - 8, 12));
            food.addCollisionListener(new Pickup(getPlayer2(), getPlayer1()));
        }

        skunk = new Skunk(this);
        skunk.setPosition(new Vec2(58,2));
        skunk.addCollisionListener(new Damage(getPlayer2(), getPlayer1()));
        skunk.startWalking(-6);

        Sound.getSpring().loop();
    }




    // Override methods
    @Override
    public Vec2 pinguPos() {
        return new Vec2(-10, 0);
    }

    @Override
    public Vec2 pandaPos() {
        return new Vec2(2.5f, 0);
    }

    @Override
    public Vec2 goalPos() {
        return new Vec2(64,1f);
    }

    @Override
    public boolean completed() {
        return (getPlayer1().getEnergyLevel() + getPlayer2().getEnergyLevel()) == FOODCOUNT;
    }

    @Override
    public int levelNumber() {
        return 2;
    }

    @Override
    public void stop() {
        super.stop();
        Sound.getSpring().stop();
    }
}
