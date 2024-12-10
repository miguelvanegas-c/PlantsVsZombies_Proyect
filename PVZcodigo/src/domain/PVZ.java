
<<<<<<< HEAD
import java.util.List;
import java.util.ArrayList;
=======

import java.util.List;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Random;
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b

/**
 * This is the principal class of the application, it will have the behaviors and functionalities of the whole game.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */

public class PVZ{
    public static final int rows = 5;
    public static final int columns = 10;
<<<<<<< HEAD
    private final List<Element>[][] board = new ArrayList[rows][columns];
    private String[] zombiesInGame, plantsInGame;
    private Plant[][] plantsBoard = new Plant[rows][columns];
    private int suns,brains;
    private List<Mover> moves = new ArrayList();
    private List<Attacker> attackers = new ArrayList();
    private List<Shooter> shooters = new ArrayList();



    /**
     * Constructor for the PVZ class MVSM game mode.
=======
    private List<Element>[][] board = new ArrayList[rows][columns];
    private String[] zombiesInGame, plantsInGame;
    private Plant[][] plantsBoard = new Plant[rows][columns];
    private int suns,brains;
    private List<Mover> moves = new ArrayList();
    private List<Attacker> attackers = new ArrayList();
    private List<Shooter> shooters = new ArrayList();



    /**
<<<<<<< HEAD
     * Constructor for the PVZ class.
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
     * Constructor for the PVZ class MVSM game mode.
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
     *
     * @param plantsInGame  Array of plants available in the game.
     * @param zombiesInGame Array of zombies available in the game.
     * @param zombieType    Boolean indicating whether zombie actions are active.
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
     * @param plantType     Boolean indicating whether plant actions are active.
     * @param startingSuns  starting suns to the game.
     * @param startingBrains starting brains to the game.
     * @param gameTime      game time to the game.
     * @param hordesNumber  number of hordes to the game
     * @param hordesTime    hordes duration time.
<<<<<<< HEAD
     */
    public PVZ(String[] plantsInGame, String[] zombiesInGame, boolean zombieType,boolean plantType,int startingSuns, int startingBrains , int gameTime , int hordesNumber, int hordesTime) {
        this.plantsInGame = plantsInGame;
        this.zombiesInGame = zombiesInGame;
        this.suns = startingSuns;
        this.brains = startingBrains;
        startingBoards();
        startingGame(zombieType,plantType,gameTime,hordesNumber,hordesTime);
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
        startingGame(zombieType,gameTime,hordesNumber,hordesTime);
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
        startingGame(gameTime);
    }

    /*
     * start game to the PvsM gameMode.
     * @param pvz, the game.
     * @param zombieType, the zombie machine to the game.
     * @param gameTime, the gameTime of the game
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame( boolean zombieType, int gameTime, int hordesNumber, int hordesTime) {

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
    private void startingGame( boolean zombieType, boolean plantType, int gameTime, int hordesNumber, int hordesTime) {

    }

    /*
     * start game to the PvsP gameMode.
     * @param pvz, the game.
     * @param gameTime, the gameTime of the game
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame( int gameTime) {

    }

=======
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
     */
    public PVZ(String[] plantsInGame, String[] zombiesInGame, boolean zombieType,boolean plantType,int startingSuns, int startingBrains , int gameTime , int hordesNumber, int hordesTime) {
        this.plantsInGame = plantsInGame;
        this.zombiesInGame = zombiesInGame;
        this.suns = startingSuns;
        this.brains = startingBrains;
        startingBoards();
        startingGame(zombieType,plantType,gameTime,hordesNumber,hordesTime);
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
        startingGame(zombieType,gameTime,hordesNumber,hordesTime);
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
        startingGame(gameTime);
    }

    /*
     * start game to the PvsM gameMode.
     * @param pvz, the game.
     * @param zombieType, the zombie machine to the game.
     * @param gameTime, the gameTime of the game
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame( boolean zombieType, int gameTime, int hordesNumber, int hordesTime) {

    }
<<<<<<< HEAD
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======

    /*
     * start game to the MvsM gameMode.
     * @param pvz, the game.
     * @param zombieType, the zombie machine to the game.
     * @param plantType, the plant machine to the game.
     * @param gameTime, the gameTime of the game.
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame( boolean zombieType, boolean plantType, int gameTime, int hordesNumber, int hordesTime) {

    }

    /*
     * start game to the PvsP gameMode.
     * @param pvz, the game.
     * @param gameTime, the gameTime of the game
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame( int gameTime) {

    }

>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
    //Getters
    public List<Element>[][] getBoard(){
        return board;
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
    public boolean getZombieType(){
        return zombieType;
    }
    public boolean getPlantType(){
        return plantType;
    }
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
    public String[] getZombiesInGame(){
        return zombiesInGame;
    }
    public String[] getPlantsInGame(){
        return plantsInGame;
    }
<<<<<<< HEAD
<<<<<<< HEAD
    public Plant[][] getPlantsBoard(){
        return plantsBoard;
    }
    public List<Mover> getMoves(){
        return moves;
    }
    public int getSuns(){return suns;}
    public int getBrains(){return brains;}
=======
    public List<Zombie>[][] getZombiesBoard(){
        return zombiesBoard;
    }
    public Plant[][] getPlantsBoard(){
        return plantsBoard;
    }
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
    public Plant[][] getPlantsBoard(){
        return plantsBoard;
    }
    public List<Mover> getMoves(){
        return moves;
    }
    public int getSuns(){return suns;}
    public int getBrains(){return brains;}
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b

    /*
     * Initializes the zombie board, setting up empty lists for each cell.
     */
<<<<<<< HEAD
<<<<<<< HEAD
    private void startingBoards(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new ArrayList<>();
=======
    private void startingZombiesBoardAndBoard(){
=======
    private void startingBoards(){
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new ArrayList<>();
<<<<<<< HEAD
                coins[i][j] = new ArrayList<>();
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
            }
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
    /**
     * Delete zombie of the board.
     * @param row, row of the zombie.
     * @param col, column of the zombie.
<<<<<<< HEAD
     */
    public void deleteZombie(int row, int col) {
        for(Element element : board[row][col]){
            if(element instanceof Zombie zombie){
                if(zombie.getLife() <= 0) {
                    moves.remove(element);
                    board[row][col].remove(zombie);
                }
                break;
=======
    /*
     * Moves zombies across the board. Zombies advance one cell to the left if possible.
     * If a zombie reaches the leftmost column, it is removed from the board.
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
     */
    public void deleteZombie(int row, int col) {
        for(Element element : board[row][col]){
            if(element instanceof Zombie zombie){
                if(zombie.getLife() <= 0) {
                    moves.remove(element);
                    board[row][col].remove(zombie);
                }
<<<<<<< HEAD
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
                break;
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
            }
        }
    }

    /**
     * Add a zombie to the board at a specified row and the last column.
     *
     * @param row    The row index where the zombie will be added.
     * @param zombie The type of zombie to be added.
<<<<<<< HEAD
<<<<<<< HEAD
     * @throws PVZException if the zombie is null or doesn't exist.
     */
    public void addZombie(int row, String zombie) throws PVZException {
        Zombie newZombie = searchZombie(zombie,row);
        valideZombieExist(newZombie);
        board[row][9].add(newZombie);
        moves.add(newZombie);
    }


    /**
     * Add a zombie to the board at a specified row and the last column, with value.
     * @param zombie The type of zombie to be added.
     * @param row    The row index where the zombie will be added.
     * @throws PVZException if the zombie is null or doesn't exist.
     */
    public void addZombie(String zombie, int row) throws PVZException {
        Zombie newZombie = searchZombie(zombie,row);
        valideZombieExist(newZombie);
        if(newZombie.getValue() <= brains) {
            board[row][9].add(newZombie);
            moves.add(newZombie);
        }else{
            throw new PVZException(PVZException.ERROR_NOT_ENOUGH_BRAINS);
        }
    }

    /*
     * Search zombie that want to add.
     * @param zombie, the string of the zombie.
     * @return Zombie, the Zombie.
     * @throws PVZException if the string is null;
     */
    private Zombie searchZombie(String zombie, int row) throws PVZException {
        valideZombieNotNull(zombie);
        switch (zombie) {
            case "zombie":
                return new BasicZombie(row);
            case "zombieCono":
                Shield cone = new Cone();
                return new ZombieWithShield(row,cone);
            case "zombieBalde":
                Shield bucket = new Bucket();
                return new ZombieWithShield(row,bucket);
            default:
                return null;
        }

    }

    /*
     * Validate that the string of the zombie isn't null.
     * @param zombie, string to validate.
     * @throws PVZException if the string is null.
     */
    private void valideZombieNotNull(String zombie) throws PVZException {
        if (zombie == null) throw new PVZException(PVZException.ERROR_ZOMBIE_NOT_SELECTED);
    }

    /*
     * Validate that the Zombie exist.
     * @param zombie, the zombie to validate.
     * @throws PVZException if the Zombie is null.
     */
    private void valideZombieExist(Zombie zombie) throws PVZException {
        if (zombie == null) throw new PVZException(PVZException.ERROR_ZOMBIE_NOT_EXIST);
    }

    /**
     * Delete plant of the board.
     * @param row, row of the plant.
     * @param col, col of the board.
     * @throws PVZException, if there isn´t a plant to delete.
     */
    public void deletePlant(int row, int col) throws PVZException {
        validePlantExistToDelete(row,col);
        Plant plant = plantsBoard[row][col];
        board[row][col].remove(plant);
        plantsBoard[row][col] = null;
    }

    /*
     * Validate if exist plant to delete.
     * @param row of the plant to delete.
     * @param col of the plant to delete
     * @throws PVZException if there isn´t a plant in this position;
     */
    private void validePlantExistToDelete(int row, int col) throws PVZException {
        if(plantsBoard[row][col] == null) throw new PVZException(PVZException.NOT_PLANT_TO_DELETE);
    }


=======
=======
     * @throws PVZException if the zombie is null or doesn't exist.
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
     */
    public void addZombie(int row, String zombie) throws PVZException {
        Zombie newZombie = searchZombie(zombie,row);
        valideZombieExist(newZombie);
        board[row][9].add(newZombie);
        moves.add(newZombie);
    }


    /**
     * Add a zombie to the board at a specified row and the last column, with value.
     * @param zombie The type of zombie to be added.
     * @param row    The row index where the zombie will be added.
     * @throws PVZException if the zombie is null or doesn't exist.
     */
    public void addZombie(String zombie, int row) throws PVZException {
        Zombie newZombie = searchZombie(zombie,row);
        valideZombieExist(newZombie);
        if(newZombie.getValue() <= brains) {
            board[row][9].add(newZombie);
            moves.add(newZombie);
        }else{
            throw new PVZException(PVZException.ERROR_NOT_ENOUGH_BRAINS);
        }
    }

    /*
     * Search zombie that want to add.
     * @param zombie, the string of the zombie.
     * @return Zombie, the Zombie.
     * @throws PVZException if the string is null;
     */
    private Zombie searchZombie(String zombie, int row) throws PVZException {
        valideZombieNotNull(zombie);
        switch (zombie) {
            case "zombie":
                return new BasicZombie(row);
            case "zombieCono":
                Shield cone = new Cone();
                return new ZombieWithShield(row,cone);
            case "zombieBalde":
                Shield bucket = new Bucket();
                return new ZombieWithShield(row,bucket);
            default:
                return null;
        }

    }
<<<<<<< HEAD
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======

    /*
     * Validate that the string of the zombie isn't null.
     * @param zombie, string to validate.
     * @throws PVZException if the string is null.
     */
    private void valideZombieNotNull(String zombie) throws PVZException {
        if (zombie == null) throw new PVZException(PVZException.ERROR_ZOMBIE_NOT_SELECTED);
    }

    /*
     * Validate that the Zombie exist.
     * @param zombie, the zombie to validate.
     * @throws PVZException if the Zombie is null.
     */
    private void valideZombieExist(Zombie zombie) throws PVZException {
        if (zombie == null) throw new PVZException(PVZException.ERROR_ZOMBIE_NOT_EXIST);
    }

    /**
     * Delete plant of the board.
     * @param row, row of the plant.
     * @param col, col of the board.
     * @throws PVZException, if there isn´t a plant to delete.
     */
    public void deletePlant(int row, int col) throws PVZException {
        validePlantExistToDelete(row,col);
        Plant plant = plantsBoard[row][col];
        board[row][col].remove(plant);
        plantsBoard[row][col] = null;
    }

    /*
     * Validate if exist plant to delete.
     * @param row of the plant to delete.
     * @param col of the plant to delete
     * @throws PVZException if there isn´t a plant in this position;
     */
    private void validePlantExistToDelete(int row, int col) throws PVZException {
        if(plantsBoard[row][col] == null) throw new PVZException(PVZException.NOT_PLANT_TO_DELETE);
    }


>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
        if(newPlant.getValue()<= suns) {
            plantsBoard[row][col] = newPlant;
            board[row][col].add(newPlant);
            suns -= newPlant.getValue();
        }else{
            throw new PVZException(PVZException.ERROR_NOT_ENOUGH_SUNS);
        }
<<<<<<< HEAD
    }



=======
        plantsBoard[row][col] = newPlant;
        board[row][col].add(newPlant);
    }

>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
    }



>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
        return switch (plant) {
            case "peashooter" -> new Peashooter(row, col);
            case "sunflower" -> new Sunflower(row, col);
            case "wallnut" -> new Wallnut(row, col);
            default -> null;
        };
<<<<<<< HEAD
=======
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
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
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
<<<<<<< HEAD
<<<<<<< HEAD
        if (plant == null) throw new PVZException(PVZException.ERROR_PLANT_NOT_SELECTED);
=======
        if (plant == null) {
            throw new PVZException(PVZException.ERROR_PLANT_NOT_SELECTED);
        }
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
        if (plant == null) throw new PVZException(PVZException.ERROR_PLANT_NOT_SELECTED);
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
    }
    /**
     * Add a coin to the board in a specific
     *
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public void addCoin(int row, int col,int finishRow, String coin) throws PVZException{
        valideCoinNotNull(coin);
        Coin newCoin = searchCoin(coin,row,col,finishRow);
        valideCoinExist(newCoin);
        board[row][col].add(newCoin);
        moves.add(newCoin);
    }

    /*
     * Search a coin with the String.
     * @param String, string of the coin.
     * @return Coin, the coin of the string.
     */
    private Coin searchCoin(String coin, int row, int col,int finishRow) throws PVZException{

        return switch (coin) {
            case "sun" -> new Sun(row, col, finishRow);
            case "brain" -> new Brain(row, col, finishRow);
            default -> null;
        };

=======
    public void addCoin(int row, int col, int startRow, int startCol, String coin) throws PVZException{
=======
    public void addCoin(int row, int col,int finishRow, String coin) throws PVZException{
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
        valideCoinNotNull(coin);
        Coin newCoin = searchCoin(coin,row,col,finishRow);
        valideCoinExist(newCoin);
        board[row][col].add(newCoin);
<<<<<<< HEAD
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
        moves.add(newCoin);
    }

    /*
     * Search a coin with the String.
     * @param String, string of the coin.
     * @return Coin, the coin of the string.
     */
    private Coin searchCoin(String coin, int row, int col,int finishRow) throws PVZException{

        return switch (coin) {
            case "sun" -> new Sun(row, col, finishRow);
            case "brain" -> new Brain(row, col, finishRow);
            default -> null;
        };

>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
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
<<<<<<< HEAD
<<<<<<< HEAD
        if (coin == null) throw new PVZException(PVZException.ERROR_COIN_EXIST);
    }

    /**
     * move the elements that can be moved in the board.
     */
    public void moveBoard(){
        for(Mover move : moves){
            Element moveElement = (Element) move;
            board[move.getRow()][move.getCol()].remove(moveElement);
            move.move();
            board[move.getRow()][move.getCol()].add(moveElement);
        }
    }

    /**
     * Take a coin of the board.
     * @param row, row of the coin.
     * @param col, col of the row.
     * @throws PVZException if not exist a coin in this cell.
     */
    public void takeCoin(int row, int col) throws PVZException{
        Coin coin = coinInCell(row,col);
        valideCoinExist(coin);
        if(coin instanceof Brain){
            brains += coin.getValue();
        }else{
            suns += coin.getValue();
        }
        board[row][col].remove(coin);
        moves.remove(coin);
    }

    /**
     * Look if a position don't have a coin.
     * @param row of the coin.
     * @param col of the coin.
     * @return if exist a coin false, if not true.
     */
    public boolean isEmptyOfCoins(int row, int col){
        for(Element element : board[row][col]) if (element instanceof Coin ) return false;
        return true;
    }

    /*
     * Take the coin of the cell.
     * @param row, row of the coin.
     * @param col, col of the coin.
     */
    private Coin coinInCell(int row, int col){
        for(Element element : board[row][col]){
            if (element instanceof Coin coin){
                return coin;
            }
        }
        return null;
    }



=======
        if (coin == null) throw new PVZException(PVZException.ERROR_COIN_NOT_EXIST);
=======
        if (coin == null) throw new PVZException(PVZException.ERROR_COIN_EXIST);
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
    }

    /**
     * move the elements that can be moved in the board.
     */
    public void moveBoard(){
        for(Mover move : moves){
            Element moveElement = (Element) move;
            board[move.getRow()][move.getCol()].remove(moveElement);
            move.move();
            board[move.getRow()][move.getCol()].add(moveElement);
        }
    }

    /**
     * Take a coin of the board.
     * @param row, row of the coin.
     * @param col, col of the row.
     * @throws PVZException if not exist a coin in this cell.
     */
    public void takeCoin(int row, int col) throws PVZException{
        Coin coin = coinInCell(row,col);
        valideCoinExist(coin);
        if(coin instanceof Brain){
            brains += coin.getValue();
        }else{
            suns += coin.getValue();
        }
        board[row][col].remove(coin);
        moves.remove(coin);
    }

    /**
     * Look if a position don't have a coin.
     * @param row of the coin.
     * @param col of the coin.
     * @return if exist a coin false, if not true.
     */
    public boolean isEmptyOfCoins(int row, int col){
        for(Element element : board[row][col]) if (element instanceof Coin ) return false;
        return true;
    }

    /*
     * Take the coin of the cell.
     * @param row, row of the coin.
     * @param col, col of the coin.
     */
    private Coin coinInCell(int row, int col){
        for(Element element : board[row][col]){
            if (element instanceof Coin coin){
                return coin;
            }
        }
        return null;
    }

<<<<<<< HEAD
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======


>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
}

