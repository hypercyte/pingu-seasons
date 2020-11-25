package game;

import city.cs.engine.DebugViewer;

import javax.swing.*;
import java.awt.*;

/**
 * Main game file.
 * Title: Pingu Seasons
 */
public class Game {

    // Level class access
    private BaseLevel world;

    // Background class access
    private BackgroundView view;

    // Level field used to identify level number.
    private int level;

    // Controllers, (Number associated with player no.)
    private Controller1 controller1;
    private Controller2 controller2;

    // Trackers, (")
    private Tracker tracker1;

    // Control panel
    private ControlPanel panel;

    public Game() {

        // Setting the level
        level = 1;
        // Creating the world
        world = new Level1();
        world.create(this);

        // Creating the background of the game.
        view = new BackgroundView(world, 1000, 500);

        // Frame settings
        final JFrame frame = new JFrame("Pingu Seasons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(view);
        frame.setResizable(false);

        // Setting initial trackers
        tracker1 = new Tracker(view, world.getPlayer1());

        // Control panel
        panel = new ControlPanel(world, tracker1, this);
        frame.add(panel.getMainGUI(), BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
        view.addMouseListener(new GiveFocus(frame));

        // Setting initial controllers
        controller1 = new Controller1(world.getPlayer1());
        controller2 = new Controller2(world.getPlayer2());

        // Both player 1 and 2 are listening out for control inputs
        frame.addKeyListener(controller1);
        frame.addKeyListener(controller2);

        // Setting initial tracker
        world.addStepListener(new Tracker(view, world.getPlayer1()));


        // world start
        world.start();
    }

    // Getters for players on the current level
    public Pingu getPlayer1() {
        return world.getPlayer1();
    }
    public Panda getPlayer2() {
        return world.getPlayer2();
    }

    // Checking for level completion
    public boolean completionCheck() {
        return world.completed();
    }

    // Getter for the level number
    public int getLevel() {
        return level;
    }

    // Level change when called by save/load feature
    public void forceChangeLevel(BaseLevel level) {
        // stop previous world
        world.stop();

        //set level to the new level written in saves.txt
        this.level = level.levelNumber();
        world = level;

        // let the background know we are on the next level !
        view.setLevel(world.levelNumber());

        // change controller bodies
        controller1.change(world.getPlayer1());
        controller2.change(world.getPlayer2());
        // change tracker body
        tracker1.change(world.getPlayer1());
        world.addStepListener(tracker1);

        // let control panel know to control the new world, not the old one.
        panel.setWorld(world);

        // set view and start
        view.setWorld(world);
        view.setSize(1000,500);
        world.start();
    }

    public void proceed() {
        // stop previous world
        world.stop();

        if (level == 3) {
            // exit the game if level 3 is completed.
            System.exit(0);
        } else if (level == 1) {
            // increase level integer variable
            level++;

            // let the background know we are on the next level !
            view.incLevel();

            // create new world
            world = new Level2();
            world.create(this);

            // change controller bodies
            controller1.change(world.getPlayer1());
            controller2.change(world.getPlayer2());

            // change tracker body
            tracker1.change(world.getPlayer1());
            world.addStepListener(tracker1);

            // let control panel know to control the new world, not the old one.
            panel.setWorld(world);

            // set view and start
            view.setWorld(world);
            view.setSize(1000,500);
            world.start();
            JFrame debugView = new DebugViewer(world, 500, 500);
        } else if (level == 2) {
            // increase level integer variable
            level++;

            // let the background know we are on the next level !
            view.incLevel();

            // create new world
            world = new Level3();
            world.create(this);

            // change controller bodies
            controller1.change(world.getPlayer1());
            controller2.change(world.getPlayer2());

            // change tracker body
            tracker1.change(world.getPlayer1());
            world.addStepListener(tracker1);

            // let control panel know to control the new world, not the old one.
            panel.setWorld(world);

            // set view and start
            view.setWorld(world);
            view.setSize(1000,500);
            world.start();
        }
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}
