

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
    private boolean zombieType, plantType;
    private String[] zombiesInGame, plantsInGame, zombiesToGenerate;
    private List<Zombie>[][] zombiesBoard = new ArrayList[rows][columns];
    private Plant[][] plantsBoard = new Plant[rows][columns];
    private List<Coin>[][] coins = new ArrayList[rows][columns];
    private Timer timerGenerateZombies, timerZombieHorde,timerGenerateZombiesInHorde;
    private int countZombiesInHorde;


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
        startingZombiesBoardAndBoard();

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
    private void startingZombiesBoardAndBoard(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                zombiesBoard[i][j] = new ArrayList<>();
                board[i][j] = new ArrayList<>();
                coins[i][j] = new ArrayList<>();
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
                            board[row][col].remove(zombie);
                            board[row][col - 1].add(zombie);
                            if (col - 1 == 0){
                                zombiesBoard[row][col - 1].remove(zombie);
                                board[row][col - 1].remove(zombie);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Add a zombie to the board at a specified row and the last column.
     *
     * @param row    The row index where the zombie will be added.
     * @param zombie The type of zombie to be added.
     */
    public void addZombie(int row, String zombie){
        Zombie newZombie = null;
        if(zombie.equals("zombie")){
            newZombie = new BasicZombie(row);
            zombiesBoard[row][9].add(newZombie);
            board[row][9].add(newZombie);

        }
    }

    /**
     * Add a plant to the board at a specified position.
     *
     * @param row    The row index for the plant.
     * @param col    The column index for the plant.
     * @param plant The type of plant to be added.
     * @throws PVZException if planting is not allowed or the cell is not empty.
     */
    public void addPlant(int row, int col, String plant)  throws PVZException{
        valideCanPlant(row,col);
        valideEmptyCell(row,col);
        Plant newPlant = searchPlant(plant,row,col);
        validePlantExist(newPlant);
        plantsBoard[row][col] = newPlant;
        board[row][col].add(newPlant);
    }

    /*
     * Search the Plant that is shown with the string.
     * @param plant, the string of the plant.
     * @param row, the row of the plant.
     * @param col, the col of the plant.
     * @return Plant, the Plant of the string.
     * @throws PVZException if the plant is null.
     */
    private Plant searchPlant(String plant,int row,int col) throws PVZException{
        validePlantNotNull(plant);
        switch  (plant){
            case "peashooter":
                return new Peashooter(row,col);
            case "sunflower":
                return new Sunflower(row,col);
            case "wallnut":
                return new Wallnut(row,col);
            default:
                return null;
        }
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

    /*
     * Validates plant exist.
     * @param plant, the plant.
     * @throws PVZException if the plant is null.
     *
     */

    private void validePlantExist(Plant plant) throws PVZException{
        if (plant == null) throw new PVZException(PVZException.ERROR_PLANT_NOT_EXIST);
    }
    /*
     * Validates that a plant was selected.
     * @param plant, String of the plant.
     * @throws PVZException if the string is null.
     */
    private void validePlantNotNull(String plant) throws PVZException{
        if (plant == null) {
            throw new PVZException(PVZException.ERROR_PLANT_NOT_SELECTED);
        }
    }
    /**
     * Add a coin to the board in a specific
     *
     */
    public void addCoin(int row, int col, int startRow, int startCol, String coin) throws PVZException{
        valideCoinNotNull(coin);
        Coin newCoin = searchCoin(coin,row,col,startRow,startCol);
        valideCoinExist(newCoin);
        coins[row][col].add(newCoin);
    }

    /*
     * Validate coin is not null.
     * @param coin, String of the coin.
     * @throws PVZException if String is null
     *
     */
    private void valideCoinNotNull(String coin) throws PVZException{
        if (coin == null) throw new PVZException(PVZException.ERROR_COIN_NULL);
    }

    /*
     * Validate coin exist.
     * @param coin, the Coin.
     * @throws PVZException if Coin doesn't exist.
     */
    private void valideCoinExist(Coin coin) throws PVZException{
        if (coin == null) throw new PVZException(PVZException.ERROR_COIN_NOT_EXIST);
    }
    /*
     * Search a coin with the String.
     * @param String, string of the coin.
     * @return Coin, the coin of the string.
     */
    private Coin searchCoin(String coin, int row, int col, int startRow, int startCol) throws PVZException{

        switch(coin){
            case "sun":
               return  new Sun(row,col,startRow,startCol);
            default:
                return null;
        }
    }

    /**
     * Starts the game by setting up timers for generating zombies and moving them.
     */
    public void startingGame(){
        generateZombiesToTheGame();
        //Generate zombie each 10 seconds.
        timerGenerateZombies = new Timer(15000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateZombies();
            }
        });
        timerGenerateZombies.start();

        //Move the zombies
        Timer timerMove = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e){

                moveZombies();
            }
        });
        timerMove.start();

        //Generate a zombie horde each minute.
        timerZombieHorde = new Timer(80000, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                generateZombiesHorde();
            }
        });
        timerZombieHorde.start();

    }

    /*
     * Randomly generates a zombie in a random row.
     */
    private void generateZombies() {
        if (countZombiesInHorde >= 9) {
            timerGenerateZombiesInHorde.stop();
            countZombiesInHorde = 0;
        }
        Random random = new Random();
        int randomNum = random.nextInt(5) ;
        addZombie(randomNum, "zombie");
    }

    /*
     * Generate a zombie horde.
     */
    private void generateZombiesHorde(){
        timerGenerateZombiesInHorde = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateZombies();
                countZombiesInHorde++;
            }
        });
        timerGenerateZombiesInHorde.start();
    }

    /*
     * Generate the list of the zombies that will play.
     */
    private void generateZombiesToTheGame(){
        zombiesToGenerate = new String[40];
        Integer[] numOfZombies = new Integer[5];
        for(String z: zombiesInGame){
            switch(z){
                case "BasicZombie":
                    numOfZombies[0] = 38;
            }
        }

    }

}

