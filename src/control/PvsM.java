/**
 * Class PvsM
 * Manages a game between plants and zombies, controlling the hordes, game time, and map events.
 */
import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

public class PvsM extends GameMode implements Serializable {
    private Timer mapSuns;

    /**
     * Constructor for PvsM.
     * Initializes the game settings and starts the necessary timers.
     *
     * @param zombieType   Type of zombies (normal or special).
     * @param hordesNumber Number of hordes in the game.
     * @param hordesTime   Duration of each horde in seconds.
     * @param pvz          Reference to the PVZ game logic.
     */
    public PvsM(boolean zombieType,int gameTime, int hordesNumber, int hordesTime, PVZ pvz) {
        super(zombieType,gameTime,hordesNumber,hordesTime,pvz);
        createTimerSuns();
    }


    /**
     * Stops all timers and ends the game.
     */
    public void finishGame() {
        mapSuns.stop();
        timePerHordes.stop();
        if(hordeDuration.isRunning()) hordeDuration.stop();
        if (timePerHordes.isRunning()) timePerHordes.stop();
        pvz = null;
    }

    /**
     * Creates and starts the timer to generate suns on the map every 10 seconds.
     */
    private void createTimerSuns() {
        mapSuns = new Timer(10000, e -> addSuns());
        mapSuns.start();
    }

    /*
     * Adds a sun to a random position on the map.
     */
    private void addSuns() {
        try {
            Random random = new Random();
            int col = random.nextInt(4) + 1;
            int row = random.nextInt(5);
            pvz.addCoin(0, col, row, "sun");
        } catch (PVZException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
