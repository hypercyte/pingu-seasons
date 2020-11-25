package game;

import city.cs.engine.*;
import javax.swing.*;
import java.awt.*;

/** Background and foreground view class
 *
 *  Displays a custom background and health/energy bars
 */

public class BackgroundView extends UserView {

    // Fields
    private Image winter, spring, fall;
    private int level;
    private Pingu pingu;
    private Panda panda;

    /**
     * Initialise background files
     * @param world The current level
     * @param width The width
     * @param height The height
     */
    BackgroundView(World world, int width, int height) {
        super(world, width, height);

        // Setting image icons
        winter = new ImageIcon("data/winter_bg.png").getImage();
        spring = new ImageIcon("data/spring_bg.png").getImage();
        fall = new ImageIcon("data/fall_bg.png").getImage();

        // Initial level declared
        level = 1;
    }

    /**
     * Increment level when player proceeds.
     */
    // Setters for the level
    public void incLevel() {
        this.level++;
    }

    /**
     * Set the level when player uses a save file.
     * @param level The saved level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /** Paints everything that goes in the background */
    @Override
    protected void paintBackground(Graphics2D g) {
        // Changes the background based on level number.
        if (level == 1) {
            g.drawImage(winter, 0,0,this);
        } else if (level == 2) {
            g.drawImage(spring, 0,0,this);
        } else if (level == 3) {
            g.drawImage(fall, 0,0,this);
        }
    }

    /** Paints everything in foreground (HUD Elements) */
    @Override
    protected void paintForeground(Graphics2D g) {
        // Health label
        g.setColor(Color.red);
        g.drawString("Health", 15, 20);

        // Health bar - Player 1
        g.fillRect(15,25,pingu.getHP() * 50,5);
        g.setColor(new Color(150, 5, 2));
        g.drawRect(15,25, 100,5); // (Static outer border)

        // Health bar - Player 2
        g.setColor(Color.red);
        g.fillRect(15,40,panda.getHP() * 50,5);
        g.setColor(new Color(150, 5, 2));
        g.drawRect(15,40, 100,5); // (Static outer border)

        // Energy label
        g.setColor(new Color(228, 100, 2));
        g.drawString("Combined Energy", 15, 70);

        // Energy Bar - Both players
        g.fillRect(15,75,(pingu.getEnergyLevel() + panda.getEnergyLevel()) * 50,5);
        g.setColor(new Color(150, 63, 0));
        // Change the static outer border length based on level number.
        if (level == 1) {
            g.drawRect(15,75, 4 * 50,5);
        } else if (level == 2) {
            g.drawRect(15,75, 8 * 50,5);
        } else if (level == 3) {
            g.drawRect(15,75, 6 * 50,5);
        }
    }
}
