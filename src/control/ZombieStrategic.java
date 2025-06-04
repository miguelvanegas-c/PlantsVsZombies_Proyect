import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * ZombieStrategic class implements the ZombieMachine interface.
 * This class manages the behavior of zombies in a Plants vs. Zombies game,
 * focusing on strategic decisions for generating and deploying zombies.
 */
public class ZombieStrategic implements Machine, Serializable {
    private PVZ pvz; // Reference to the game logic and board
    private Timer generateZombie;
    private List<String> zombiesCanUse; // List of zombies available in the current game
    private List<String> zombieTank = new ArrayList<>(); // List of tank-type zombies
    private String zombieShooter; // Specific shooter-type zombie
    private String zombieCoins; // Specific coin-generating zombie
    private int brains; // Brains resource available for creating zombies
    /**
     * Constructor for ZombieStrategic. Initializes zombies based on the game's context
     * and starts the zombie generation process.
     *
     * @param pvz Reference to the game's logic and board.
     */
    public ZombieStrategic(PVZ pvz) {
        this.pvz = pvz;
        zombiesCanUse = Arrays.asList(pvz.getZombiesInGame());
        if (zombiesCanUse.contains("brainstein")) zombieCoins = "brainstein";
        if (zombiesCanUse.contains("eciZombie")) zombieShooter = "eciZombie";
        if (zombiesCanUse.contains("zombie")) zombieTank.add("zombie");
        if (zombiesCanUse.contains("zombieCono")) zombieTank.add("zombieCono");
        if (zombiesCanUse.contains("zombieBalde")) zombieTank.add("zombieBalde");
        brains = pvz.getBrains();
        startGame();
    }

    /**
     * Starts the zombie generation timer. Zombies are generated every 10 seconds.
     */
    private void startGame() {
        generateZombie = new Timer(10000, e -> zombieAction());
        generateZombie.start();
    }

    /**
     * Generates a zombie or a group of zombies and places them on the game board.
     */
    public void generateZombie() {
        try {
            int targetRow = determineBestRow();
            List<String> newZombie = determineZombieType(targetRow);

            for (String targetZombie : newZombie) {
                pvz.addZombie(targetZombie, targetRow);
                brains = pvz.getBrains();
            }
        } catch (PVZException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Determines the best row to deploy zombies based on the defenses and shooters in each row.
     *
     * @return The row index with the weakest defense.
     */
    private int determineBestRow() {
        int bestRow = -1;
        int minDefenseScore = Integer.MAX_VALUE;

        for (int row = 0; row < PVZ.rows; row++) {
            int defenseScore = calculateDefenseScore(row);
            if (defenseScore < minDefenseScore) {
                minDefenseScore = defenseScore;
                bestRow = row;
            }
        }

        return bestRow;
    }

    /**
     * Calculates the defense score of a row based on the presence of plants.
     *
     * @param row The row index to evaluate.
     * @return The calculated defense score.
     */
    private int calculateDefenseScore(int row) {
        int defenseCount = 0;

        for (int col = 0; col < PVZ.columns; col++) {
            Plant plant = pvz.getPlantsBoard()[row][col];
            if (plant instanceof Wallnut) {
                defenseCount += 3;
            }
            if (plant instanceof Peashooter) {
                defenseCount++;
            }
            if ( plant instanceof PotatoMine) {
                defenseCount += 2;
            }
        }

        return defenseCount ;
    }

    /**
     * Determines the type(s) of zombies to generate based on the target row and available resources.
     *
     * @param row The row index where zombies will be deployed.
     * @return A list of zombie types to deploy.
     */
    private List<String> determineZombieType(int row) {
        List<String> zombies = new ArrayList<>();
        Random random = new Random();
        int index = random.nextInt(zombieTank.size());

        if (hasManyShooters(row)) {
            if (brains >= 450) {
                if (!zombieTank.isEmpty()) zombies.add(zombieTank.get(index));
                if (zombieShooter != null) zombies.add(zombieShooter);
            } else if (brains >= 250) {
                if (!zombieTank.isEmpty()) zombies.add(zombieTank.get(index));
                if (zombieCoins != null) zombies.add(zombieCoins);
            }
        } else {
            if (brains >= 500) {
                if (!zombieTank.isEmpty()) zombies.add(zombieTank.get(index));
                if (zombieShooter != null) zombies.add(zombieShooter);
                if (zombieCoins != null) zombies.add(zombieCoins);
            } else if (brains >= 250) {
                if (!zombieTank.isEmpty()) zombies.add(zombieTank.get(index));
                if (zombieCoins != null) zombies.add(zombieCoins);
            }
        }

        return zombies;
    }

    /**
     * Determines if a row has many attacking plants.
     *
     * @param row The row index to evaluate.
     * @return True if the row has more than 2 attacking plants, otherwise false.
     */
    private boolean hasManyShooters(int row) {
        int shooterCount = 0;

        for (int col = 0; col < PVZ.columns; col++) {
            Plant plant = pvz.getPlantsBoard()[row][col];
            if (plant instanceof Peashooter) {
                shooterCount++;
            }
        }

        return shooterCount > 2;
    }

    /**
     * Executes zombie-related actions, such as adding brains and collecting coins.
     */
    private void zombieAction() {
        try {
            brains += 50;
            pvz.setBrains(50);

            for (int row = 0; row < PVZ.rows; row++) {
                pvz.takeCoin(row, 9);
                brains = pvz.getBrains();
            }

            generateZombie();
        } catch (PVZException e) {
            // No se maneja debido a que es una excepcion experada.
        }
    }

    /**
     * Stops the zombie generation process at the end of the game.
     */
    @Override
    public void finishGame() {
        generateZombie.stop();
        pvz = null;
    }
}
