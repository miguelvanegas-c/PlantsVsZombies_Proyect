

import java.util.List;
import java.util.ArrayList;

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
    private String[] zombiesInGame, plantsInGame;
    private Plant[][] plantsBoard = new Plant[rows][columns];
    private int suns,brains;
    private List<Move> moves = new ArrayList();



    /**
     * Constructor for the PVZ class MVSM game mode.
     *
     * @param plantsInGame  Array of plants available in the game.
     * @param zombiesInGame Array of zombies available in the game.
     * @param zombieType    Boolean indicating whether zombie actions are active.
     * @param plantType     Boolean indicating whether plant actions are active.
     * @param startingSuns  starting suns to the game.
     * @param startingBrains starting brains to the game.
     * @param gameTime      game time to the game.
     * @param hordesNumber  number of hordes to the game
     * @param hordesTime    hordes duration time.
     */
    public PVZ(String[] plantsInGame, String[] zombiesInGame, boolean zombieType,boolean plantType,int startingSuns, int startingBrains , int gameTime , int hordesNumber, int hordesTime) {
        this.plantsInGame = plantsInGame;
        this.zombiesInGame = zombiesInGame;
        this.suns = startingSuns;
        this.brains = startingBrains;
        startingBoards();
        startingGame(this,zombieType,plantType,gameTime,hordesNumber,hordesTime);
    }

    /**
     * Constructor for the PVZ class PVSM game mode.
     *
     * @param plantsInGame  Array of plants available in the game.
     * @param zombiesInGame Array of zombies available in the game.
     * @param zombieType    Boolean indicating whether zombie actions are active.
     * @param startingSuns  starting suns to the game.
     * @param startingBrains starting brains to the game.
     * @param gameTime      game time to the game.
     * @param hordesNumber  number of hordes to the game
     * @param hordesTime    hordes duration time.
     */

    public PVZ(String[] plantsInGame, String[] zombiesInGame, boolean zombieType,int startingSuns, int startingBrains , int gameTime , int hordesNumber, int hordesTime) {
        this.plantsInGame = plantsInGame;
        this.zombiesInGame = zombiesInGame;
        this.suns = startingSuns;
        this.brains = startingBrains;
        startingBoards();
        startingGame(this,zombieType,gameTime,hordesNumber,hordesTime);
    }

    /**
     * Constructor for the PVZ class PVSP game mode.
     *
     * @param plantsInGame  Array of plants available in the game.
     * @param zombiesInGame Array of zombies available in the game.
     * @param startingSuns  starting suns to the game.
     * @param startingBrains starting brains to the game.
     * @param gameTime      game time to the game.
     */

    public PVZ(String[] plantsInGame, String[] zombiesInGame,int startingSuns, int startingBrains , int gameTime) {
        this.plantsInGame = plantsInGame;
        this.zombiesInGame = zombiesInGame;
        this.suns = startingSuns;
        this.brains = startingBrains;
        startingBoards();
        startingGame(this,gameTime);
    }

    /*
     * start game to the PvsM gameMode.
     * @param pvz, the game.
     * @param zombieType, the zombie machine to the game.
     * @param gameTime, the gameTime of the game
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame(PVZ pvz, boolean zombieType, int gameTime, int hordesNumber, int hordesTime) {

    }

    /*
     * start game to the MvsM gameMode.
     * @param pvz, the game.
     * @param zombieType, the zombie machine to the game.
     * @param plantType, the plant machine to the game.
     * @param gameTime, the gameTime of the game.
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame(PVZ pvz, boolean zombieType, boolean plantType, int gameTime, int hordesNumber, int hordesTime) {

    }

    /*
     * start game to the PvsP gameMode.
     * @param pvz, the game.
     * @param gameTime, the gameTime of the game
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame(PVZ pvz, int gameTime) {

    }

    //Getters
    public List<Element>[][] getBoard(){
        return board;
    }
    public String[] getZombiesInGame(){
        return zombiesInGame;
    }
    public String[] getPlantsInGame(){
        return plantsInGame;
    }
    public Plant[][] getPlantsBoard(){
        return plantsBoard;
    }
    public List<Move> getMoves(){
        return moves;
    }
    public int getSuns(){return suns;}
    public int getBrains(){return brains;}

    /*
     * Initializes the zombie board, setting up empty lists for each cell.
     */
    private void startingBoards(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new ArrayList<>();
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
            board[row][9].add(newZombie);
            moves.add(newZombie);
        }
    }

    private Zombie searchZombie(String zombie, int row){
        Zombie newZombie = null;
        switch (zombie) {
            case "zombie":
                newZombie = new BasicZombie(row);
        }
        return newZombie;
    }
    /**
     * Add a plant to the board at a specified position.
     *
     * @param row    The row index for the plant.
     * @param col    The column index for the plant.
     * @param plant The type of plant to be added.
     * @throws PVZException if planting is not allowed or the cell is not empty.
     */
    public  void addPlant(int row, int col, String plant)  throws PVZException{
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
    public void addCoin(int row, int col,int finishRow, String coin) throws PVZException{
        valideCoinNotNull(coin);
        Coin newCoin = searchCoin(coin,row,col,finishRow);
        valideCoinExist(newCoin);
        board[row][col].add(newCoin);
        moves.add(newCoin);
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
    private Coin searchCoin(String coin, int row, int col,int finishRow) throws PVZException{

        switch(coin){
            case "sun":
               return  new Sun(row,col,finishRow);
            default:
                return null;
        }
    }

    public void moveBoard(){
        for(Move move : moves){
            Element moveElement = (Element) move;
            board[move.getRow()][move.getCol()].remove(moveElement);
            move.move();
            board[move.getRow()][move.getCol()].add(moveElement);
        }
    }



}

