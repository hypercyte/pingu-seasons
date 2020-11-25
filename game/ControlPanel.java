package game;

import city.cs.engine.SoundClip;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

/** Shared control panel for the players */

public class ControlPanel {
    private JButton pauseButton;
    private JButton quitButton;
    private JComboBox<String> trackerSelection;

    private BaseLevel curLevel;
    private Game game;

    private JPanel mainGUI;
    private JButton unpauseButton;
    private JComboBox comboBox1;
    private JButton saveButton;
    private JButton loadButton;
    private JSlider volSlider;
    private JLabel volLabel;
    private JButton volMute;
    private JLabel gameTitle;

    /**
     * Initialise the control panel
     * @param world The current level
     * @param tracker The current tracker
     * @param g The game
     */
    public ControlPanel(BaseLevel world, Tracker tracker, Game g) {
        game = g;
        curLevel = world;
        pauseButton.addActionListener(new ActionListener() {
            /** Pause game */
            @Override
            public void actionPerformed(ActionEvent e) {
               world.stop();
            }
        });
        unpauseButton.addActionListener(new ActionListener() {
            /** Resume game */
            @Override
            public void actionPerformed(ActionEvent e) {
                world.start();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            /** Quit game when quit button is pressed */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        comboBox1.addItemListener(new ItemListener() {
            /**
             * Invoked when an item is clicked in the drop down menu
             * @param e the dropdown menu item list.
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem() == "Player 1") {
                    System.out.println("PLAYER 1 VIEW");
                    tracker.change(curLevel.getPlayer1());
                    world.addStepListener(tracker);
                } else if (e.getItem() == "Player 2") {
                    System.out.println("PLAYER 2 VIEW");
                    tracker.change(curLevel.getPlayer2());
                    world.addStepListener(tracker);
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            /**
             * Invoked when save button is pressed
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSaver s = new GameSaver("data/saves.txt", curLevel);
                try {
                    s.saveGame(curLevel);
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        loadButton.addActionListener(new ActionListener() {
            /**
             * Invoked when load button is pressed
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLoader l = new GameLoader("data/saves.txt", g, curLevel);
                try {
                    BaseLevel loadedGame = l.loadGame();
                    game.forceChangeLevel(loadedGame);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });


        volMute.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //Sound.getWinter().setVolume(0.0001);
                volSlider.setValue(1);
            }
        });

        volSlider.addChangeListener(new ChangeListener() {
            /**
             * Invoked when the target of the listener has changed its state.
             *
             * @param e a ChangeEvent object
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                float volume = scale(volSlider.getValue(), 1, 10, 0.0001f, 2);
                Sound.getWinter().setVolume(volume);
                Sound.getFall().setVolume(volume);
                Sound.getSpring().setVolume(volume);

                Sound.getPain().setVolume(volume);
                Sound.getNom().setVolume(volume);
                Sound.getSmash().setVolume(volume);
                Sound.getDing().setVolume(volume);
            }
        });
    }

    /**
     * Calculates the intermediate values for the volume slider.
     *
     * @param value The value we want.
     * @param min The minimum input value from the slider
     * @param max The maximum input value from the slider
     * @param minRange The minimum range of the slider
     * @param maxRange The maximum range of the slider
     * @return The final scaled value
     */
    private float scale(float value, float min, float max, float minRange, float maxRange) {
        return ((maxRange - minRange) * (value - min) / (max - min)) + minRange;
    }

    /**
     * Set the level field
     * @param level The current level
     */
    public void setWorld(BaseLevel level) {
        curLevel = level;
    }

    /**
     * Getter to retrieve the control panel and initialise it in the game
     * @return the GUI control panel
     */
    public JPanel getMainGUI() {
        return mainGUI;
    }

}
