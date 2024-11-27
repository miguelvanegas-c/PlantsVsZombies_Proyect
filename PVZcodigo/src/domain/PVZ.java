

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
/**
 * This is the principal class of the application, it will have the behaviors and functionalities of the whole game.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */

public class PVZ{
    public static final int rows = 5;
    public static final int columns = 10;
    private List<Element>[][] board = new ArrayList[rows][columns];
    private boolean zombieType;
    private boolean plantType;
    private String[] zombiesInGame;
    private String[] plantsInGame;
    private List<Zombie>[][] zombiesBoard = new ArrayList[rows][columns];
    private Plant[][] plantsBoard = new Plant[rows][columns];
    private Coin[][] coins = new Coin[rows][columns];

    /**
     * Constructor for the PVZ class.
     *
     * @param plantsInGame  Array of plants available in the game.
     * @param zombiesInGame Array of zombies available in the game.
     * @param zombieType    Boolean indicating whether zombie actions are active.
     */
    public PVZ(String[] plantsInGame, String[] zombiesInGame, boolean zombieType) {
        this.plantsInGame = plantsInGame;
        this.zombiesInGame = zombiesInGame;
        this.zombieType = zombieType;
        startingZombiesBoard();

    }
    //Getters
    public List<Element>[][] getBoard(){
        return board;
    }
    public boolean getZombieType(){
        return zombieType;
    }
    public boolean getPlantType(){
        return plantType;
    }
    public String[] getZombiesInGame(){
        return zombiesInGame;
    }
    public String[] getPlantsInGame(){
        return plantsInGame;
    }
    public List<Zombie>[][] getZombiesBoard(){
        return zombiesBoard;
    }
    public Plant[][] getPlantsBoard(){
        return plantsBoard;
    }

    /*
     * Initializes the zombie board, setting up empty lists for each cell.
     */
    private void startingZombiesBoard(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                zombiesBoard[i][j] = new ArrayList<>();
            }
        }
    }

    /*
     * Moves zombies across the board. Zombies advance one cell to the left if possible.
     * If a zombie reaches the leftmost column, it is removed from the board.
     */
    private void moveZombies() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int len = zombiesBoard[row][col].size();
                for (int i = 0; i < len; i++) {
                    if(i < zombiesBoard[row][col].size()) {
                        Zombie zombie = zombiesBoard[row][col].get(i);
                        zombie.move();
                        int x = zombie.getXPosition();
                        if (((x - 140) % 70) == 0) {
                            zombiesBoard[row][col].remove(zombie);
                            zombiesBoard[row][col - 1].add(zombie);
                            if (col - 1 == 0) zombiesBoard[row][col - 1].remove(zombie);
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds a zombie to the board at a specified row and the last column.
     *
     * @param row    The row index where the zombie will be added.
     * @param zombie The type of zombie to be added.
     */
    public void addZombie(int row, String zombie){
        Zombie newZombie = null;
        if(zombie.equals("zombie")){
            newZombie = new BasicZombie(row);
            zombiesBoard[row][9].add(newZombie);
            Element newZombieElement = (Element) newZombie;
            if (board[row][9] == null) board[row][9] = new ArrayList<>();
            board[row][9].add(newZombieElement);

        }
    }

    /**
     * Adds a plant to the board at a specified position.
     *
     * @param row    The row index for the plant.
     * @param col    The column index for the plant.
     * @param planta The type of plant to be added.
     * @throws PVZException if planting is not allowed or the cell is not empty.
     */
    public void addPlant(int row, int col, String planta)  throws PVZException{
        valideCanPlant(row,col);
        valideEmptyCell(row,col);
        Plant newPlant = null;
        if (planta.equals("peashooter")) newPlant = new Peashooter(row, col);
        plantsBoard[row][col] = newPlant;
        Element newPlantElement = (Element) newPlant;
        if (board[row][col] == null) {board[row][col] = new ArrayList<>();}
        board[row][col].add(newPlantElement);
    }

    /*
     * Validates whether a plant can be placed in the specified cell.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @throws PVZException if planting is not allowed in the specified cell.
     */
    private void valideCanPlant(int row, int col) throws PVZException{
        if(col == 0 || col == columns-1 ) throw new PVZException(PVZException.ERROR_CANT_PLANT);
    }

    /*
     * Validates whether a cell is empty before planting.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @throws PVZException if the cell is not empty.
     */
    private void valideEmptyCell(int row, int col) throws PVZException{
        if(plantsBoard[row][col] != null) throw new PVZException(PVZException.ERROR_CELDA_NOT_EMPTY);
    }

    /**
     * Starts the game by setting up timers for generating zombies and moving them.
     */
    public void startingGame(){
        Timer timerGenerateZombies = new Timer(7000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateZombies();
            }
        });
        timerGenerateZombies.start();

        Timer timerMove = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                moveZombies();
            }
        });
        timerMove.start();

    }

    /*
     * Randomly generates a zombie in a random row.
     */
    private void generateZombies() {
        Random random = new Random();
        int randomNum = random.nextInt(5) ;
        addZombie(randomNum, "zombie");
    }


}
