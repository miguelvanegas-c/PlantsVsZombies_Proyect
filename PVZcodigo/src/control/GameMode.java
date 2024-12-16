import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class GameMode {
    protected int hordesNumber;
    protected int hordesTime;
    protected int ordesIndex = 0;
    protected int gameTime;
    protected PVZ pvz;
    protected Timer timePerHordes;
    protected Timer hordeDuration;
    protected List<String> zombiesPlaying = new ArrayList<>();
    protected List<String> zombiesCanUse;

    public GameMode(boolean zombieType,int gameTime, int hordesNumber, int hordesTime, PVZ pvz) {
        this.hordesNumber = hordesNumber;
        this.hordesTime = hordesTime;
        this.pvz = pvz;
        this.gameTime = gameTime;
        zombiesCanUse = Arrays.asList(pvz.getZombiesInGame());
        if(zombieType) zombiesCanUse.remove("brainstein");
        generateHordes();
    }
    public abstract void finishGame();
    /*
     * Initializes the timer to generate hordes at intervals based on the total game time and number of hordes.
     */
    private void generateHordes() {
        int hordDiff = ((gameTime - 50) * 1000) / 2;
        timePerHordes = new Timer(hordDiff, e -> throwHorde());
        timePerHordes.start();
    }

    /*
     * Starts a horde and schedules zombies to be added to the map during the horde.
     */
    private void throwHorde() {
        doHorde();
        int zombDiff = (hordesTime * 1000) / hordesNumber;
        hordeDuration = new Timer(zombDiff, e -> addZombie());
        hordeDuration.start();
    }

    /*
     * Prepares a list of zombies for the current horde.
     * Randomly selects 10 zombies from the available types.
     */
    private void doHorde() {
        int zombieNumber = zombiesCanUse.size();
        Random random = new Random();
        int intRandom = random.nextInt(zombieNumber);
        int count = hordesNumber ;
        while (count != 0) {
            zombiesPlaying.add(zombiesCanUse.get(intRandom));
            intRandom = random.nextInt(zombieNumber);
            count--;
        }
    }

    /*
     * Adds a zombie to a random row on the map and handles horde duration.
     */
    private void addZombie() {
        String zombie = zombiesPlaying.removeFirst();
        Random random = new Random();
        int intRandom = random.nextInt(5);
        try {
            ordesIndex++;
            pvz.addZombie(intRandom, zombie);
            if (ordesIndex == hordesNumber) {
                ordesIndex = 0;
                hordeDuration.stop();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
