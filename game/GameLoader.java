package game;

import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Game loader
 */
public class GameLoader {

    // Stores the file name
    /** File name */
    private String fileName;

    // Allows the main game file to be accessed
    /** Main game */
    private Game game;

    // Allows access to the level.
    /** Current level */
    private BaseLevel level;

    /**
     * Initialise a new Game loader
     * @param fileName the name of the saves file.
     */
    public GameLoader(String fileName, Game g, BaseLevel level) {
        this.fileName = fileName;
        game = g;
        this.level = level;
    }

    /**
     * Read the save data from the saves file and initiate change of game level
     * to the new level found in the save file
     */
    public BaseLevel loadGame() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();

            // Split line into different tokens separated by the comma symbol
            String[] tokens = line.split(",");

            // Stores first token, which is the level number
            int levelNumber = Integer.parseInt(tokens[0]);

            // The 2nd and 3rd values are for player 1's position
            float xPlayer1 = Float.parseFloat(tokens[1]);
            float yPlayer1 = Float.parseFloat(tokens[2]);
            Vec2 posPlayer1 = new Vec2(xPlayer1, yPlayer1);

            // The 4th and 5th values are for player 2's position
            float xPlayer2 = Float.parseFloat(tokens[3]);
            float yPlayer2 = Float.parseFloat(tokens[4]);
            Vec2 posPlayer2 = new Vec2(xPlayer2, yPlayer2);

            // Load game based on save data
            BaseLevel level = null;
            if (levelNumber == 1) {
                level = new Level1();
                level.create(game);
                level.getPlayer1().setPosition(posPlayer1);
                level.getPlayer2().setPosition(posPlayer2);
            }
            else if (levelNumber == 2) {
                level = new Level2();
                level.create(game);
                level.getPlayer1().setPosition(posPlayer1);
                level.getPlayer2().setPosition(posPlayer2);
            }
            else if (levelNumber == 3) {
                level = new Level3();
                level.create(game);
                level.getPlayer1().setPosition(posPlayer1);
                level.getPlayer2().setPosition(posPlayer2);
            }

            return level;

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}
