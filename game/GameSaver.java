package game;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Game Saver
 */
public class GameSaver {

    // Stores the file name
    /** File name */
    private String fileName;

    // Stores current level
    /** Current level */
    private BaseLevel curLevel;

    /**
     * Initialise a new game save
     * @param fileName the name of the saves file.
     * @param level the current level player is on whilst saving.
     */
    public GameSaver(String fileName, BaseLevel level) {
        this.fileName = fileName;
        curLevel = level;
    }

    /**
     * Write to the save data from the saves file.
     */
    public void saveGame(BaseLevel level) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
            writer.write(level.levelNumber() + "," +
                    level.getPlayer1().getPosition().x + "," + level.getPlayer1().getPosition().y + "," +
                    level.getPlayer2().getPosition().x + "," + level.getPlayer2().getPosition().y + "\n");

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public void setWorld(BaseLevel level) {
        curLevel = level;
    }

}
