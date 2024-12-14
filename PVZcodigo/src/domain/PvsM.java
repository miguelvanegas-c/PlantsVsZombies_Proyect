/**
 * Class PvsM
 * Manages a game between plants and zombies, controlling the hordes, game time, and map events.
 */
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PvsM {
    private boolean zombieType;
    private int gameTime;
    private int hordesNumber;
    private int hordesTime;
    private int ordesIndex = 0;
    private PVZ pvz;
    private Timer gameTimer;
    private Timer mapSuns;
    private Timer timePerHordes;
    private Timer hordeDuration;
    private String[] zombiesCanUse;
    private List<String> zombiesPlaying = new ArrayList<>();

    /**
     * Constructor for PvsM.
     * Initializes the game settings and starts the necessary timers.
     *
     * @param zombieType   Type of zombies (normal or special).
     * @param gameTime     Total game time in seconds.
     * @param hordesNumber Number of hordes in the game.
     * @param hordesTime   Duration of each horde in seconds.
     * @param pvz          Reference to the PVZ game logic.
     * @param zombiesCanUse Array of zombie types available for the game.
     */
    public PvsM(boolean zombieType, int gameTime, int hordesNumber, int hordesTime, PVZ pvz, String[] zombiesCanUse) {
        this.zombieType = zombieType;
        this.gameTime = gameTime;
        this.hordesNumber = hordesNumber;
        this.hordesTime = hordesTime;
        this.pvz = pvz;
        this.zombiesCanUse = zombiesCanUse;
        createTimerGameTime();
        createTimerSuns();
        generateHordes();
    }

    /**
     * Creates and starts the game timer that tracks the total game duration.
     */
    private void createTimerGameTime() {
        gameTimer = new Timer(gameTime * 1000, e -> finishGame());
        gameTimer.start();
    }

    /**
     * Stops all timers and ends the game.
     */
    private void finishGame() {
        gameTimer.stop();
        mapSuns.stop();
        timePerHordes.stop();
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

    /*
     * Initializes the timer to generate hordes at intervals based on the total game time and number of hordes.
     */
    private void generateHordes() {
        int hordDiff = ((gameTime - 30) * 1000) / hordesNumber;
        timePerHordes = new Timer(hordDiff, e -> throwHorde());
        timePerHordes.start();
    }

    /*
     * Starts a horde and schedules zombies to be added to the map during the horde.
     */
    private void throwHorde() {
        doHorde();
        int zombDiff = (hordesTime * 1000) / 10;
        hordeDuration = new Timer(zombDiff, e -> addZombie());
        hordeDuration.start();
    }

    /*
     * Prepares a list of zombies for the current horde.
     * Randomly selects 10 zombies from the available types.
     */
    private void doHorde() {
        int zombieNumber = zombiesCanUse.length;
        Random random = new Random();
        int intRandom = random.nextInt(zombieNumber);
        int count = 9;
        while (count != 0) {
            zombiesPlaying.add(zombiesCanUse[intRandom]);
            intRandom = random.nextInt(zombieNumber);
            count--;
        }
    }

    /*
     * Adds a zombie to a random row on the map and handles horde duration.
     */
    private void addZombie() {
        String zombie = zombiesPlaying.remove(0);
        Random random = new Random();
        int intRandom = random.nextInt(5);
        try {
            ordesIndex++;
            pvz.addZombie(intRandom, zombie);
            if (ordesIndex == 9) {
                ordesIndex = 0;
                hordeDuration.stop();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
