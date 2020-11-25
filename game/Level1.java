package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * Level 1
 */

public class Level1 extends BaseLevel {

    private static final int FOODCOUNT = 4;

    public static int getFOODCOUNT() {
        return FOODCOUNT;
    }

    // Create level
    @Override
    public void create(Game game) {
        super.create(game);

        // make the ground
        Shape groundShape = new BoxShape(11, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setFillColor(new Color(222,222,222));
        ground.setPosition(new Vec2(0, -11.5f));

        // walls
        Shape leftWallShape = new BoxShape(0.5f, 12, new Vec2(-11.5f, 11.5f));
        Fixture leftWall = new SolidFixture(ground, leftWallShape);
        Shape rightWallShape = new BoxShape(0.5f, 12, new Vec2(11.5f, 11.5f));
        Fixture rightWall = new SolidFixture(ground, rightWallShape);
        Shape separatorShape = new BoxShape(0.5f, 5.5f, new Vec2(0, 6));
        Fixture separator = new SolidFixture(ground, separatorShape);

        // platforms
        Shape boxShape = new BoxShape(3, 0.5f);
        Body platform1 = new StaticBody(this, boxShape);
        platform1.setPosition(new Vec2(-8, 5.5f));
        Body platform2 = new StaticBody(this, boxShape);
        platform2.setPosition(new Vec2(3.5f, -2.5f));
        Body platform3 = new StaticBody(this, boxShape);
        platform3.setPosition(new Vec2(8, -6.5f));
        Body platform4 = new StaticBody(this, boxShape);
        platform4.setPosition(new Vec2(-3.5f, -2.5f));
        Body platform5 = new StaticBody(this, boxShape);
        platform5.setPosition(new Vec2(-8, -6.5f));
        Body platform6 = new StaticBody(this, boxShape);
        platform6.setPosition(new Vec2(-8, 10.5f));

        // Creating break-able barriers.
        // -- Wood requires 2 energy to break.
        // -- Metal requires 4 energy to break (across both players)
        Body wood = new Wood(this);
        wood.setPosition(new Vec2(-6.5f, 8));
        wood.addCollisionListener(new Destroy(getPlayer2(), getPlayer1()));
        Body metal = new Metal(this);
        metal.setPosition(new Vec2(6, -9));
        metal.addCollisionListener(new Destroy(getPlayer2(), getPlayer1()));

        // Creating traps
        Body ice = new Ice(this);
        ice.setPosition(new Vec2(-10, -5f));
        ice.addCollisionListener(new Damage(getPlayer2(), getPlayer1()));
        Body ice2 = new Ice(this);
        ice2.setPosition(new Vec2(10, -5f));
        ice2.addCollisionListener(new Damage(getPlayer2(), getPlayer1()));

        // Food generation
        for (int i = 0; i < FOODCOUNT; i++) {
            Body food = new Food(this);
            food.setPosition(new Vec2(i*6f-10, 8));
            food.addCollisionListener(new Pickup(getPlayer2(), getPlayer1()));
        }

        Sound.getWinter().loop();
    }


    // Override methods
    @Override
    public Vec2 pinguPos() {
        return new Vec2(-10, -10);
    }

    @Override
    public Vec2 pandaPos() {
        return new Vec2(2.5f, -10);
    }

    @Override
    public Vec2 goalPos() {
        return new Vec2(9,-9.25f);
    }

    @Override
    public boolean completed() {
        return (getPlayer1().getEnergyLevel() + getPlayer2().getEnergyLevel()) == FOODCOUNT;
    }

    @Override
    public int levelNumber() {
        return 1;
    }

    @Override
    public void stop() {
        super.stop();
        Sound.getWinter().stop();
    }
}
